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
        "x-rapidapi-host: deep-translate1.p.rapidapi.com",
        "x-rapidapi-key: 84c45541a2mshf075bfc1c3ab1f1p1a5cf0jsn84c68bb3ef37"
    )
    @GET("languages")
    fun getLanguages(): Call<LanguageList>

    @Headers(
        "x-rapidapi-host: deep-translate1.p.rapidapi.com",
        "x-rapidapi-key: 84c45541a2mshf075bfc1c3ab1f1p1a5cf0jsn84c68bb3ef37"
    )
    @POST("https://deep-translate1.p.rapidapi.com/language/translate/v2")
    fun translate(
        @Body request: TranslationRequest
    ): Call<TranslationResult>
}