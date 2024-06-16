package br.com.rodorush.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.rodorush.model.api.TranslatorRapidApiClient
import br.com.rodorush.model.domain.ApiError
import br.com.rodorush.model.domain.TranslationRequest
import br.com.rodorush.model.livedata.TranslatorLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.HttpURLConnection.HTTP_OK

class TranslatorViewModel : ViewModel() {

    private val _apiError = MutableLiveData<ApiError>()
    val apiError: LiveData<ApiError> get() = _apiError

    fun getLanguages() = viewModelScope.launch(Dispatchers.IO) {
        TranslatorRapidApiClient.service.getLanguages().execute().also { response ->
            if (response.code() == HTTP_OK) {
                response.body()?.also { languageList ->
                    TranslatorLiveData.languageListLiveData.postValue(languageList)
                }
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                _apiError.postValue(ApiError(response.code(), errorMessage))
            }
        }
    }

    fun translate(query: String, source: String, target: String, format: String) =
        viewModelScope.launch(Dispatchers.IO) {
            val request = TranslationRequest(query, source, target, format)
            TranslatorRapidApiClient.service.translate(request).execute()
                .also { response ->
                    if (response.code() == HTTP_OK) {
                        response.body()?.let { translationResult ->
                            TranslatorLiveData.translationResultLiveData.postValue(translationResult)
                        }
                    } else {
                        val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                        _apiError.postValue(ApiError(response.code(), errorMessage))
                    }
                }
        }
}