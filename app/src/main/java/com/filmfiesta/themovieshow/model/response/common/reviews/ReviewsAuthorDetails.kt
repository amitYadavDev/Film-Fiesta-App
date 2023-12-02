package com.filmfiesta.themovieshow.model.response.common.reviews


import com.google.gson.annotations.SerializedName

data class ReviewsAuthorDetails(
    @SerializedName("avatar_path")
    val avatarPath: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("username")
    val username: String
)