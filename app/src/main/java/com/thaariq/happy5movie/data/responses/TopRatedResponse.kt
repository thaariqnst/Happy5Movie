package com.thaariq.happy5movie.data.responses

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class TopRatedMovieResponse(

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("results")
    val results: List<TopRatedMovieItem>? = null,

    @field:SerializedName("total_results")
    val totalResults: Int? = null
) : Parcelable

@Parcelize
data class TopRatedMovieItem(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,
) : Parcelable