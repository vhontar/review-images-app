package com.vhontar.reviewimagesapp.data.network.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.SocketTimeoutException

internal suspend inline fun makeSafeCall(crossinline block: suspend CoroutineScope.() -> HandledResult): HandledResult {
    return withContext(Dispatchers.IO) {
        try {
            block.invoke(this)
        } catch (e: SocketTimeoutException) {
            HandledResult.ServerTimeoutException
        } catch (e: Throwable) {
            HandledResult.GeneralException(e)
        }
    }
}

internal fun <T: Any> Response<T>.handle(): HandledResult {
    return when {
        isSuccessful -> HandledResult.Success(this.body())
        errorBody() != null -> HandledResult.ResponseError() // parse errorBody
        else -> HandledResult.GeneralException()
    }
}