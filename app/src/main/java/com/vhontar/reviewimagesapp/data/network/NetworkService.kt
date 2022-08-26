package com.vhontar.reviewimagesapp.data.network

import com.vhontar.reviewimagesapp.data.network.response.HitsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("")
    suspend fun fetchImages(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("image_type") imageType: String = "photo"
    ): Response<HitsResponse>

    @GET("")
    suspend fun fetchImage(
        @Query("id") id: String,
        @Query("image_type") imageType: String = "photo"
    ): Response<HitsResponse>
}