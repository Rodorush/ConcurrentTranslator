package br.com.rodorush.model.domain

data class LanguageList(
    val data: Data
) {
    data class Data(
        val languages: List<Language>
    ) {
        data class Language(
            val language: String
        )
    }
}