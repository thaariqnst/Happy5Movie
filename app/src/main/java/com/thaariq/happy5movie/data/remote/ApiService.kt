package com.thaariq.happy5movie.data.remote

import com.thaariq.happy5movie.BuildConfig
import com.thaariq.happy5movie.data.responses.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("genre/movie/list")
    fun getGenres(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Call<GenreResponse>

    @GET("movie/now_playing")
    fun getShowing(
        @Query("api_key")
        apiKey : String = BuildConfig.API_KEY
    ): Call<ShowingMovieResponse>

    @GET("movie/top_rated")
    fun getTopRated(
        @Query("api_key")
        apiKey : String  = BuildConfig.API_KEY
    ): Call<TopRatedMovie>

    @GET("movie/{movie_id}")
    fun getDetails(
        @Path("movie_id")
        movie_id : Int?,
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Call<DetailResponse>

    @GET("movie/{movie_id}/credits")
    fun getCredits(
        @Path("movie_id")
        movie_id : Int?,
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Call<CreditsResponse>
}