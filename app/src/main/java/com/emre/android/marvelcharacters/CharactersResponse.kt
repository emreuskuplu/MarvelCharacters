package com.emre.android.marvelcharacters

data class CharactersResponse(
    val data: Data
)

data class Data(
    val results: List<Character>
)

data class Character(
    val id: Int,
    val name: String,
    val thumbnail: Thumbnail,
)

data class Thumbnail(
    val path: String,
    val extension: String
)

