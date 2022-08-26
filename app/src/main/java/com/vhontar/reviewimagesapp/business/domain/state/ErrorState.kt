package com.vhontar.reviewimagesapp.business.domain.state

import java.lang.Exception

data class ErrorState(
    val handledError: HandledError? = null,
    val exception: Exception? = null
)