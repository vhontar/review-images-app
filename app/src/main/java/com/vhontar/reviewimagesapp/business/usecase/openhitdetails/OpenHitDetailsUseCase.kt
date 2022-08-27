package com.vhontar.reviewimagesapp.business.usecase.openhitdetails

import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.business.usecase.common.SimpleUseCase
import com.vhontar.reviewimagesapp.business.usecase.common.SuspendUseCase

interface OpenHitDetailsUseCase: SuspendUseCase<HitModel, Unit>