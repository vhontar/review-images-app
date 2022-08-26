package com.vhontar.reviewimagesapp.business.usecase.loadhit

import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.business.domain.request.HitRequestModel
import com.vhontar.reviewimagesapp.business.domain.state.DataState
import com.vhontar.reviewimagesapp.business.usecase.common.SuspendUseCase

interface LoadHitUseCase: SuspendUseCase<HitRequestModel, DataState<HitModel?>>