package com.emre.android.marvelcharacters

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class MainViewModel {
    val charactersLiveData = MutableLiveData<List<Character>>()

    fun fetchCharacters() {
        CharactersRepo.fetchCharacters()
            .enqueue(object : Callback<CharactersResponse> {
                override fun onResponse(call: Call<CharactersResponse>, response: Response<CharactersResponse>) {
                    charactersLiveData.value = response.body()?.data?.results
                }

                override fun onFailure(call: Call<CharactersResponse>, t: Throwable) {
                    Log.e(javaClass.simpleName, t.message ?: "Response failed")
                }

            })

        thread {
            Log.i(javaClass.simpleName, CharactersRepo.fetchCharacters().request().url().toString())
        }
    }
}