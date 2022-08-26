package com.vhontar.reviewimagesapp.business.usecase.common

interface SimpleUseCase<K, V> {
    operator fun invoke(data: K?): V
}