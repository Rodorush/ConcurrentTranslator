package br.com.rodorush.model.api

import br.com.rodorush.model.domain.LanguageList
import br.com.rodorush.model.domain.TranslationResult
import retrofit2.Call
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
    @POST
    fun translate(
        @Query("q") query: String,
        @Query("source") source: String,
        @Query("target") target: String,
        @Query("format") format: String
    ): Call<TranslationResult>
}