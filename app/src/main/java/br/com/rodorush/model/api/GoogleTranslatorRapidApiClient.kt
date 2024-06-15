package br.com.rodorush.model.api

object GoogleTranslatorRapidApiClient {
    private const val BASE_URL = "https://google-translator9.p.rapidapi.com/v2"

    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
}