package com.emre.android.marvelcharacters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel : ViewModel() {
    private val disposables = CompositeDisposable()
    var characterList = mutableListOf<Character>()
    val charactersLiveData = MutableLiveData<List<Character>>()
    val characterDetailLiveData = MutableLiveData<List<CharacterDetail>>()
    val characterComicsLiveData = MutableLiveData<List<Comic>>()
    var updateDataPosition = 24
    var isCurrentlyUpdate = true

    init {
        CharactersRepo
            .fetchCharacters()
            .map {
                it.data.results
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                characterList.addAll(it)
                charactersLiveData.value = characterList
                isCurrentlyUpdate = false
            }
            .addTo(disposables)
    }

    fun subscribeCharactersData(updateDataObservable: Observable<Unit>) {
        updateDataObservable
            .observeOn(Schedulers.io())
            .concatMap {
                isCurrentlyUpdate = true
                CharactersRepo.fetchCharacters()
            }
            .map {
                it.data.results
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                characterList.addAll(it)
                charactersLiveData.value = it
                updateDataPosition += 30
                isCurrentlyUpdate = false
            }
            .addTo(disposables)
    }

    fun fetchCharacterDetailData(characterId: Int) {
        CharactersRepo
            .fetchCharacterDetail(characterId)
            .map {
                it.data.results
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                characterDetailLiveData.value = it
            }
            .addTo(disposables)
    }

    fun fetchCharacterComicsData(characterId: Int) {
        CharactersRepo
            .fetchCharacterComics(characterId)
            .map {
                it.data.results
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                characterComicsLiveData.value = it
            }
            .addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}