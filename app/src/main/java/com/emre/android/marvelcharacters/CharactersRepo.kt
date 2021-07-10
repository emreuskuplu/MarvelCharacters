package com.emre.android.marvelcharacters

import retrofit2.Call

object CharactersRepo {
    private const val API_KEY = "c5b225858f011ac3056c291a6c7a415c"
    private const val HASH = "901114ebaefd5af2837d422d72c48aba"
    private const val TS = "thesoer"
    private const val LIMIT = "30"

    fun fetchCharacters(): Call<CharactersResponse> {
        return CharactersRetrofit.charactersApiService
            .fetchCharacters(TS, API_KEY, HASH, LIMIT)
    }
}