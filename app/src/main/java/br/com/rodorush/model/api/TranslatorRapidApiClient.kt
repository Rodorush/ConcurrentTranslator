package br.com.rodorush.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TranslatorRapidApiClient {
    private const val BASE_URL = "https://google-translator9.p.rapidapi.com/v2/"

    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
        GsonConverterFactory.create()
    ).build()

    val service: TranslatorRapidApiService = retrofit.create(TranslatorRapidApiService::class.java)
}