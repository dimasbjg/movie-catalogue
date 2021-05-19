package com.example.moviecatalogue.data.source.local.entity

import androidx.room.*
import com.example.moviecatalogue.utils.EntityType


@Entity(tableName = "movies")
data class MovieEntity(
    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "rating")
    var rating: Double? = null,

    @ColumnInfo(name = "imgPoster")
    var imgPoster: String? = null,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String? = null,

    @ColumnInfo(name = "status")
    var status: String? = null,

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "type")
    var type: Int? = EntityType.TYPE_MOVIE
)