package com.thaariq.happy5movie.data.responses

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class CreditsResponse(

    @field:SerializedName("cast")
    val cast: List<CastItem>? = null,

    @field:SerializedName("id")
    val id: Int? = null,
) : Parcelable


@Parcelize
data class CastItem(

    @field:SerializedName("profile_path")
    val profilePath: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("character")
    val character: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,
) : Parcelable
