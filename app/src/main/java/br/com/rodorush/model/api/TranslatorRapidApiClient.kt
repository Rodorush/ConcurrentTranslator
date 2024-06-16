package br.com.rodorush.model.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TranslatorRapidApiClient {
    private const val BASE_URL = "https://deep-translate1.p.rapidapi.com/language/translate/v2/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    private val retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).client(httpClient).addConverterFactory(
            GsonConverterFactory.create()
        ).build()

    val service: TranslatorRapidApiService = retrofit.create(TranslatorRapidApiService::class.java)
}