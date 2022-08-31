package com.vhontar.reviewimagesapp.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vhontar.reviewimagesapp.business.data.cache.HitsCacheDataSource
import com.vhontar.reviewimagesapp.business.data.network.HitsNetworkDataSource
import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.business.domain.request.HitsRequestModel
import com.vhontar.reviewimagesapp.business.usecase.loadlastquery.LoadLastQueryUseCase
import com.vhontar.reviewimagesapp.business.utils.AppConstants
import com.vhontar.reviewimagesapp.datasource.paging.HitsPagingSource
import com.vhontar.reviewimagesapp.view.utils.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkDataSource: HitsNetworkDataSource,
    private val cacheDataSource: HitsCacheDataSource,
    private val loadLastQueryUseCase: LoadLastQueryUseCase
) : ViewModel() {
    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading.asLiveData()

    val lastQuery = MutableLiveData<String>()

    private val _noItemsFound = MutableLiveData(false)
    val noItemsFound = _noItemsFound.asLiveData()

    init {
        viewModelScope.launch {
            val query = loadLastQueryUseCase.invoke(null)
            generateHitsRequestModel(query)
            lastQuery.postValue(query)
        }
    }

    private var hitsRequestModel = HitsRequestModel(AppConstants.DEFAULT_REQUEST)

    fun fetchHits(): Flow<PagingData<HitModel>> {
        val pageConfig = PagingConfig(pageSize = AppConstants.DEFAULT_IMAGES_PER_PAGE)

        return Pager(
            config = pageConfig,
            pagingSourceFactory = {
                HitsPagingSource(
                    networkDataSource,
                    cacheDataSource,
                    loadRequestModel = { hitsRequestModel },
                    noItems = { _noItemsFound.postValue(it) }
                )
            }
        ).flow.cachedIn(viewModelScope)
    }

    fun generateHitsRequestModel(query: String) {
        hitsRequestModel = HitsRequestModel(query)
    }

    fun setLoading(loading: Boolean) {
        this._loading.postValue(loading)
    }

    fun clear() {
        lastQuery.postValue("")
    }
}