package com.vhontar.reviewimagesapp.data.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vhontar.reviewimagesapp.data.network.convertors.toModels
import com.vhontar.reviewimagesapp.data.network.common.HandledResult
import com.vhontar.reviewimagesapp.data.network.paging.exceptions.ResponseException
import com.vhontar.reviewimagesapp.data.network.paging.exceptions.WrongResponseTypeException
import com.vhontar.reviewimagesapp.data.network.repository.ImagesRepository
import com.vhontar.reviewimagesapp.data.network.response.HitsResponse
import com.vhontar.reviewimagesapp.domain.models.HitModel
import com.vhontar.reviewimagesapp.utils.AppConstants
import javax.inject.Inject

class ImagesPagingSource(
    private val query: String
) : PagingSource<Int, HitModel>() {

    @Inject
    lateinit var repository: ImagesRepository

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HitModel> {
        val key = params.key ?: AppConstants.DEFAULT_PAGE_INDEX
        return try {
            val result = repository.fetchImages(
                query = query,
                page = key,
                perPage = params.loadSize
            )

            when(result) {
                is HandledResult.Success<*> -> {
                    if (result.data is HitsResponse) {
                        val hitModels: List<HitModel> = result.data.hits?.toModels() ?: listOf()
                        LoadResult.Page(
                            data = hitModels,
                            nextKey = key + 1,
                            prevKey = key - 1
                        )
                    } else {
                        LoadResult.Error(WrongResponseTypeException())
                    }
                }
                is HandledResult.ResponseError -> LoadResult.Error(ResponseException(result.error))
                is HandledResult.GeneralException -> LoadResult.Error(result.exception)
                is HandledResult.ServerTimeoutException -> LoadResult.Error(result.exception)
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