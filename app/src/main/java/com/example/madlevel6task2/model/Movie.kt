package com.example.madlevel6task2.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page") var page: Int,
    @SerializedName("results") var results: List<Movie>,
    @SerializedName("total_pages") var total_pages: Int,
    @SerializedName("total_results") var total_results: Int
)

data class Movie(
    @SerializedName("poster_path") var poster_path: String,
    @SerializedName("adult") var adult: String,
    @SerializedName("overview") var overview: String,
    @SerializedName("release_date") var release_date: String,
    @SerializedName("genre_ids") var genre_ids: List<Int>,
    @SerializedName("id") var id: String,
    @SerializedName("original_title") var original_title: String,
    @SerializedName("original_language") var original_language: String,
    @SerializedName("title") var title: String,
    @SerializedName("backdrop_path") var backdrop_path: String,
    @SerializedName("popularity") var popularity: Double,
    @SerializedName("vote_count") var vote_count: Int,
    @SerializedName("video") var video: String,
    @SerializedName("vote_average") var vote_average: Double
)