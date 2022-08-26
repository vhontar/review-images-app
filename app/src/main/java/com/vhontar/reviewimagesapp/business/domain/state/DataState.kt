package com.vhontar.reviewimagesapp.business.domain.state

import java.lang.Exception

data class DataState<T>(
    val data: T? = null,
    val errorState: ErrorState? = null
) {
    companion object {
        fun <T> error(
            handledError: HandledError? = null,
            exception: Exception? = null
        ): DataState<T> {
            return DataState(
                errorState = ErrorState(handledError, exception)
            )
        }

        fun <T> data(
            data: T? = null
        ): DataState<T> {
            return DataState(
                data = data
            )
        }
    }
}