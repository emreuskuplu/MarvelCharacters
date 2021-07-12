package com.emre.android.marvelcharacters

import io.reactivex.rxjava3.core.Observable

object CharactersRepo {
    private const val API_KEY = "c5b225858f011ac3056c291a6c7a415c"
    private const val HASH = "901114ebaefd5af2837d422d72c48aba"
    private const val TS = "thesoer"
    private const val LIMIT_CHARACTERS = "30"
    private const val LIMIT_COMICS = "10"
    private var offset = 0

    fun fetchCharacters(): Observable<CharactersResponse> {
        return CharactersRetrofit.charactersApiService
            .fetchCharacters(TS, API_KEY, HASH, LIMIT_CHARACTERS, offset).doAfterNext { offset += 30 }
    }

    fun fetchCharacterDetail(characterId: Int): Observable<CharacterDetailResponse> {
        return CharactersRetrofit.charactersApiService
            .fetchCharacterDetail(TS, API_KEY, HASH, characterId)
    }

    fun fetchCharacterComics(characterId: Int): Observable<CharacterComicsResponse> {
        return CharactersRetrofit.charactersApiService
            .fetchCharacterComics(characterId, TS, API_KEY, HASH, LIMIT_COMICS)
    }
}