package com.vhontar.reviewimagesapp.data.network.repository

import com.vhontar.reviewimagesapp.data.network.NetworkService
import com.vhontar.reviewimagesapp.data.network.common.HandledResult
import com.vhontar.reviewimagesapp.data.network.common.handle
import com.vhontar.reviewimagesapp.data.network.common.makeSafeCall
import javax.inject.Inject

class ImagesRepository @Inject constructor(
    private val networkService: NetworkService
) {
    suspend fun fetchImages(query: String, page: Int, perPage: Int): HandledResult = makeSafeCall {
        networkService.fetchImages(
            query = query,
            page = page,
            perPage = perPage
        ).handle()
    }

    suspend fun fetchImage(id: String): HandledResult = makeSafeCall {
        networkService.fetchImage(id = id).handle()
    }
}