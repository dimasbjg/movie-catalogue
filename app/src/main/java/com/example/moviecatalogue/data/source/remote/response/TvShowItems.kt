package com.example.moviecatalogue.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class TvShowItems(
	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
) : Parcelable