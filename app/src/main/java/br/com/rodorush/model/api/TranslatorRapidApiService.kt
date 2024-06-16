package br.com.rodorush.model.api

import br.com.rodorush.model.domain.LanguageList
import br.com.rodorush.model.domain.TranslationRequest
import br.com.rodorush.model.domain.TranslationResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface TranslatorRapidApiService {
    @Headers(
        "x-rapidapi-host:google-translator9.p.rapidapi.com",
        "x-rapidapi-key: chave"
    )
    @GET("languages")
    fun getLanguages(): Call<LanguageList>

    @Headers(
        "x-rapidapi-host:google-translator9.p.rapidapi.com",
        "x-rapidapi-key: chave"
    )
    @POST(".")
    fun translate(
        @Body request: TranslationRequest
    ): Call<TranslationResult>
}