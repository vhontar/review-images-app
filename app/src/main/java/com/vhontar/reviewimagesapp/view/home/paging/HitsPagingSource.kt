package com.vhontar.reviewimagesapp.view.home.paging

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
    private val loadRequestModel: () -> HitsRequestModel
) : PagingSource<Int, HitModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HitModel> {
        val key = params.key ?: AppConstants.DEFAULT_PAGE_INDEX
        return try {
            val result = networkDataSource.fetchHits(
                hitsRequestModel = loadRequestModel.invoke(),
                page = key,
                perPage = AppConstants.DEFAULT_IMAGES_PER_PAGE
                // params.loadSize (don't want to load 3 * loadSize = 60 elements at the beginning)
            )

            if (result.errorState != null) {
                val hits = cacheDataSource.fetchAll()

                LoadResult.Page(
                    data = hits,
                    nextKey = null,
                    prevKey = null
                )
            } else {
                val hits = result.data ?: listOf()

                // cache only the first page result MAIN thread
                if (key == 1 && hits.isNotEmpty()) {
                    cacheDataSource.deleteAll()
                    cacheDataSource.insertAll(hits)
                }

                LoadResult.Page(
                    data = hits,
                    nextKey = if (hits.isEmpty()) null else key + AppConstants.DEFAULT_PAGE_INDEX,
                    prevKey = if (key == 1) null else key - AppConstants.DEFAULT_PAGE_INDEX
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