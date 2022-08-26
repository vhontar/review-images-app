package com.vhontar.reviewimagesapp.data.network.paging.exceptions

import com.vhontar.reviewimagesapp.data.network.common.HandledError
import java.lang.Exception

class ResponseException(
    val handledError: HandledError? = null
): Throwable()