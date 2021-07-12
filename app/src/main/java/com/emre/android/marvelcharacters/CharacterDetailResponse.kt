package com.emre.android.marvelcharacters

data class CharacterDetailResponse(
    val data: DataDetail
)

data class DataDetail(
    val results: List<CharacterDetail>
)

data class CharacterDetail(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: ThumbnailDetail,
    val comics: Comics
)

data class ThumbnailDetail(
    val path: String,
    val extension: String
)

data class Comics(
    val returned: Int,
    val items: List<ComicItems>
)

data class ComicItems(
    val resourceURI: String
)
