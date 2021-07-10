package com.emre.android.marvelcharacters

object Utils {
    fun appendExtensionJpgToThumbnailUrl(imageUrl: String): String {
        return "$imageUrl/portrait_incredible.jpg"
    }
}