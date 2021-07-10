package com.emre.android.marvelcharacters

data class CharactersResponse(
    val data: Data
)

data class Data(
    val results: List<Character>
)

data class Character(
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    val comics: Comics
)

data class Thumbnail(
    val path: String
)

data class Comics(
    val returned: Int,
    val items: List<Items>
)

data class Items(
    val resourceURI: String,
    val name: String
)

