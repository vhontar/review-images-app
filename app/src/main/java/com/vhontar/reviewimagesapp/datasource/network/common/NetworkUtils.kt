package com.vhontar.reviewimagesapp.datasource.network.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import java.net.SocketTimeoutException

internal suspend inline fun <T> makeSafeCall(crossinline block: suspend CoroutineScope.() -> NetworkHandledResult<T>): NetworkHandledResult<T> {
    return withContext(Dispatchers.IO) {
        try {
            block.invoke(this)
        } catch (e: SocketTimeoutException) {
            NetworkHandledResult.ResponseError(exception = e)
        } catch (e: HttpException) {
            NetworkHandledResult.ResponseError(exception = e)
        } catch (e: IOException) {
            NetworkHandledResult.ResponseError(exception = e)
        } catch (e: Throwable) {
            NetworkHandledResult.ResponseError()
        }
    }
}

internal fun <T: Any> Response<T>.handle(): NetworkHandledResult<T> {
    return when {
        isSuccessful -> NetworkHandledResult.Success(this.body())
        errorBody() != null -> NetworkHandledResult.ResponseError() // parse errorBody
        else -> NetworkHandledResult.ResponseError(exception = Exception("Something went wrong"))
    }
}