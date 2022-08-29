package com.vhontar.reviewimagesapp.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.business.domain.request.HitsRequestModel
import com.vhontar.reviewimagesapp.business.usecase.loadhits.LoadHitsUseCase
import com.vhontar.reviewimagesapp.business.usecase.loadlastquery.LoadLastQueryUseCase
import com.vhontar.reviewimagesapp.business.utils.AppConstants
import com.vhontar.reviewimagesapp.view.utils.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadHitsUseCase: LoadHitsUseCase,
    private val loadLastQueryUseCase: LoadLastQueryUseCase
) : ViewModel() {
    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading.asLiveData()

    private val _lastQueryInvoke = MutableLiveData<String>()
    val lastQuery = _lastQueryInvoke.asLiveData()

    init {
        viewModelScope.launch {
            val query = loadLastQueryUseCase.invoke(null)
            generateHitsRequestModel(query)
            _lastQueryInvoke.postValue(query)
        }
    }

    var hitsRequestModel = HitsRequestModel(AppConstants.DEFAULT_REQUEST)
        private set

    fun fetchHits(): Flow<PagingData<HitModel>> = loadHitsUseCase.invoke { hitsRequestModel }

    fun generateHitsRequestModel(query: String) {
        hitsRequestModel = HitsRequestModel(query)
    }

    fun setLoading(loading: Boolean) {
        this._loading.postValue(loading)
    }
}