package com.vhontar.reviewimagesapp.data.network.common

sealed class HandledResult {
    class Success<T>(val data: T?): HandledResult()
    class ResponseError(val error: HandledError? = null): HandledResult()
    class ServerTimeoutException(val exception: Throwable): HandledResult()
    class GeneralException(val exception: Throwable): HandledResult()
}