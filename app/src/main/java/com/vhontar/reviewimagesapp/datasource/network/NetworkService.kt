package com.vhontar.reviewimagesapp.datasource.network

import com.vhontar.reviewimagesapp.datasource.network.response.HitResponse
import com.vhontar.reviewimagesapp.datasource.network.response.HitsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("/api/")
    suspend fun fetchHits(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("image_type") imageType: String = "photo"
    ): Response<HitsResponse>

    @GET("/api/")
    suspend fun fetchHit(
        @Query("id") id: Int,
        @Query("image_type") imageType: String = "photo"
    ): Response<HitResponse>
}