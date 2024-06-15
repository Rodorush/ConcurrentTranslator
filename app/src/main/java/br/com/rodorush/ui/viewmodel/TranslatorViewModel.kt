package br.com.rodorush.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.rodorush.model.api.TranslatorRapidApiClient
import br.com.rodorush.model.livedata.TranslatorLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.HttpURLConnection.HTTP_OK

class TranslatorViewModel : ViewModel() {
    fun getLanguages() = viewModelScope.launch(Dispatchers.IO) {
        TranslatorRapidApiClient.service.getLanguages().execute().also { response ->
            if (response.code() == HTTP_OK) {
                response.body()?.also { languageList ->
                    TranslatorLiveData.languageListLiveData.postValue(languageList)
                }
            }
        }
    }

    fun translate(query: String, source: String, target: String, format: String) =
        viewModelScope.launch(Dispatchers.IO) {
            TranslatorRapidApiClient.service.translate(query, source, target, format).execute()
                .also { response ->
                    if (response.code() == HTTP_OK) {
                        response.body()?.let { translationResult ->
                            TranslatorLiveData.translationResultLiveData.postValue(translationResult)
                        }
                    }
                }
        }
}