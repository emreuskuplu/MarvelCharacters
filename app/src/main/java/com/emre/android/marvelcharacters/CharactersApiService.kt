package com.emre.android.marvelcharacters

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApiService {

    @GET("/v1/public/characters")
    fun fetchCharacters(
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: String
    ): Call<CharactersResponse>
}