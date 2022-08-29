package com.vhontar.reviewimagesapp.view.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.business.domain.request.HitRequestModel
import com.vhontar.reviewimagesapp.business.usecase.loadhit.LoadHitUseCase
import com.vhontar.reviewimagesapp.view.utils.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HitDetailsViewModel @Inject constructor(
    private val loadHitUseCase: LoadHitUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val hitId = savedStateHandle.get<Int>("hitId") ?: 0

    private val _model = MutableLiveData<HitModel>()
    val model = _model.asLiveData()

    init { loadHit() }

    fun loadHit() = viewModelScope.launch {
        val dataState = loadHitUseCase.invoke(HitRequestModel(hitId))
        dataState.data?.let { model ->
            _model.postValue(model)
        }
    }
}