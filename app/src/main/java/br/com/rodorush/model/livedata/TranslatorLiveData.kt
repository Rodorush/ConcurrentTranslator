package br.com.rodorush.model.livedata

import androidx.lifecycle.MutableLiveData
import br.com.rodorush.model.domain.LanguageList
import br.com.rodorush.model.domain.TranslationResult

object TranslatorLiveData {
    val languageListLiveData = MutableLiveData<LanguageList>()
    val translationResultLiveData = MutableLiveData<TranslationResult>()
}