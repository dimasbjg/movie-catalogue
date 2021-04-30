package com.example.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(
    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("status")
    val status: String
)