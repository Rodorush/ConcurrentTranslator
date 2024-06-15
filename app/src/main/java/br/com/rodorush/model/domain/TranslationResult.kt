package br.com.rodorush.model.domain

data class TranslationResult(
    val data: Data
) {
    data class Data(
        val translations: List<Translation>
    ) {
        data class Translation(
            val translatedText: String
        )
    }
}