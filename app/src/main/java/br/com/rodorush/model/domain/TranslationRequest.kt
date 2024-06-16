package br.com.rodorush.model.domain

data class TranslationRequest(
    val q: String,
    val source: String,
    val target: String
)