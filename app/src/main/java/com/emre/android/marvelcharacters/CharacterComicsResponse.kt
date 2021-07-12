package com.emre.android.marvelcharacters

data class CharacterComicsResponse(
val data: DataComics
)

data class DataComics(
    val results: List<Comic>
)

data class Comic(
    val thumbnail: ThumbnailComic
)

data class ThumbnailComic(
    val path: String,
    val extension: String
)