package br.com.rodorush.model.domain

data class LanguageList(
    val languages: List<Language>
) {
    data class Language(
        val language: String,
        val name: String
    ) {
        override fun toString(): String {
            return name
        }
    }
}