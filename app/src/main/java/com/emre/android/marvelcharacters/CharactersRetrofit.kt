package com.emre.android.marvelcharacters

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object CharactersRetrofit {
    private const val BASE_URL = "https://gateway.marvel.com:443/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    val charactersApiService: CharactersApiService = retrofit
        .create(CharactersApiService::class.java)
}