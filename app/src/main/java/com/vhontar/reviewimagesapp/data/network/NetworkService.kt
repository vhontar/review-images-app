package com.vhontar.reviewimagesapp.data.network

import com.vhontar.reviewimagesapp.data.network.response.HitsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("/api/")
    fun fetchImages(
        @Query("key") key: String,
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("order") order: Int,
        @Query("lang") lang: String,
        @Query("image_type") imageType: String = "photo"
    ): Response<HitsResponse>

    @GET("/api/")
    fun fetchImage(
        @Query("key") key: String,
        @Query("id") query: String,
        @Query("lang") lang: String,
        @Query("image_type") imageType: String = "photo"
    ): Response<HitsResponse>
}