package com.vhontar.reviewimagesapp.business.usecase.common

interface SuspendUseCase<K, V> {
    suspend operator fun invoke(data: K?): V
}