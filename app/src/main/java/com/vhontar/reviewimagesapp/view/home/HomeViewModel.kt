package com.vhontar.reviewimagesapp.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.vhontar.reviewimagesapp.business.domain.request.HitsRequestModel
import com.vhontar.reviewimagesapp.business.usecase.loadhits.LoadHitsUseCase
import com.vhontar.reviewimagesapp.view.utils.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    loadHitsUseCase: LoadHitsUseCase
): ViewModel() {
    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading.asLiveData()

    private var hitsRequestModel = HitsRequestModel("fruits")
    val hits = loadHitsUseCase.invoke(hitsRequestModel).cachedIn(viewModelScope)

    fun generateHitsRequestModel(query: String) {
        hitsRequestModel = HitsRequestModel(query)
    }

    fun setLoading(loading: Boolean) {
        this._loading.postValue(loading)
    }
}