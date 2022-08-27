package com.vhontar.reviewimagesapp.view.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.business.usecase.loadhit.LoadHitUseCase
import com.vhontar.reviewimagesapp.view.utils.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HitDetailsViewModel @Inject constructor(
    private val loadHitUseCase: LoadHitUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val hitId = savedStateHandle.get<Int>("hitId") ?: 0

    private val _model = MutableLiveData<HitModel>()
    val model = _model.asLiveData()


}