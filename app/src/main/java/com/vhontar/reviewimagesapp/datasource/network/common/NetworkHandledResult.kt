package com.vhontar.reviewimagesapp.datasource.network.common

import com.vhontar.reviewimagesapp.business.domain.state.HandledError

// for simplicity
sealed class NetworkHandledResult<out T> {
    class Success<T>(val data: T?): NetworkHandledResult<T>()
    class ResponseError(
        val error: HandledError? = null,
        val exception: Exception? = null
    ): NetworkHandledResult<Nothing>()
}