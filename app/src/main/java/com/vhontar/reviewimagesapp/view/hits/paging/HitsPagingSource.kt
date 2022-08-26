package com.vhontar.reviewimagesapp.view.hits.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vhontar.reviewimagesapp.business.data.cache.HitsCacheDataSource
import com.vhontar.reviewimagesapp.business.data.network.HitsNetworkDataSource
import com.vhontar.reviewimagesapp.business.domain.models.HitModel
import com.vhontar.reviewimagesapp.business.domain.request.HitsRequestModel
import com.vhontar.reviewimagesapp.utils.AppConstants

class HitsPagingSource(
    private val networkDataSource: HitsNetworkDataSource,
    private val cacheDataSource: HitsCacheDataSource,
    private val hitsRequestModel: HitsRequestModel
) : PagingSource<Int, HitModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HitModel> {
        val key = params.key ?: AppConstants.DEFAULT_PAGE_INDEX
        return try {
            val result = networkDataSource.fetchHits(
                hitsRequestModel = hitsRequestModel,
                page = key,
                perPage = params.loadSize
            )

            if (result.errorState != null) {
                LoadResult.Error(result.errorState.exception ?: IllegalArgumentException())
            } else {
                val list = result.data ?: listOf()

                // cache only the first page result
                if (key == 1 && list.isNotEmpty()) {
                    cacheDataSource.deleteAll()
                    cacheDataSource.insertAll(list)
                }

                LoadResult.Page(
                    data = result.data ?: listOf(),
                    nextKey = key + 1,
                    prevKey = key - 1
                )
            }

        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, HitModel>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}