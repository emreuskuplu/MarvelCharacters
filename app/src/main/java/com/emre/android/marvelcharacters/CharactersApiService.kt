package com.emre.android.marvelcharacters

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApiService {

    @GET("/v1/public/characters")
    fun fetchCharacters(
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: String,
        @Query("offset") offset: Int
    ): Observable<CharactersResponse>

    @GET("/v1/public/characters")
    fun fetchCharacterDetail(
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("id") id: Int
    ) : Observable<CharacterDetailResponse>

    @GET("/v1/public/characters/{id}/comics")
    fun fetchCharacterComics(
        @Path("id") id: Int,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: String
    ) : Observable<CharacterComicsResponse>
}