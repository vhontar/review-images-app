package com.vhontar.reviewimagesapp.business.usecase.loadhits

import androidx.paging.PagingData
import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.business.domain.request.HitsRequestModel
import com.vhontar.reviewimagesapp.business.usecase.common.SimpleUseCase
import kotlinx.coroutines.flow.Flow

interface LoadHitsUseCase: SimpleUseCase<HitsRequestModel?, Flow<PagingData<HitModel>>>