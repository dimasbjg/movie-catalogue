package com.example.moviecatalogue.data.source.local

data class TvShowEntity(
    var title: String? = null,
    var description: String? = null,
    var rating: Double? = null,
    var imgPoster: String? = null,
    var releaseDate: String? = null,
    var status: String? = null,
    var id: Int
)
