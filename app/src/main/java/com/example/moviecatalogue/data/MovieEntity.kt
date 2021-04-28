package com.example.moviecatalogue.data

data class MovieEntity(
    var title: String = "",
    var description: String = "",
    var rating: String = "",
    var director: String = "",
    var writter: String = "",
    var starring: String = "",
    var imgPoster: Int = 0
)