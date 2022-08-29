package com.vhontar.reviewimagesapp.datasource.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.vhontar.reviewimagesapp.business.data.cache.HitsCacheDataSource
import com.vhontar.reviewimagesapp.business.data.network.HitsNetworkDataSource
import com.vhontar.reviewimagesapp.business.domain.models.HitRemoteKeyModel
import com.vhontar.reviewimagesapp.business.domain.request.HitsRequestModel
import com.vhontar.reviewimagesapp.datasource.database.entities.HitDatabaseEntity
import com.vhontar.reviewimagesapp.business.utils.AppConstants
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class HitsRemoteMediator(
    private val networkDataSource: HitsNetworkDataSource,
    private val cacheDataSource: HitsCacheDataSource,
    private val loadRequestModel: () -> HitsRequestModel
): RemoteMediator<Int, HitDatabaseEntity>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, HitDatabaseEntity>): MediatorResult {
        val requestModel = loadRequestModel.invoke()
        return try {
            // The network load method takes an optional String
            // parameter. For every page after the first, pass the String
            // token returned from the previous page to let it continue
            // from where it left off. For REFRESH, pass null to load the
            // first page.
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                // In this example, you never need to prepend, since REFRESH
                // will always load the first page in the list. Immediately
                // return, reporting end of pagination.
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                // Query remoteKeyDao for the next RemoteKey.
                LoadType.APPEND -> {
                    val remoteKey = cacheDataSource.withTransaction {
                        cacheDataSource.fetchHitRemoteKeyByQuery(loadRequestModel.invoke().query)
                    }

                    // You must explicitly check if the page key is null when
                    // appending, since null is only valid for initial load.
                    // If you receive null for APPEND, that means you have
                    // reached the end of pagination and there are no more
                    // items to load.
                    if (remoteKey.nextKey == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }

                    remoteKey.nextKey
                }
            }

            // Suspending network load via Retrofit. This doesn't need to
            // be wrapped in a withContext(Dispatcher.IO) { ... } block
            // since Retrofit's Coroutine CallAdapter dispatches on a
            // worker thread.
            val response = networkDataSource.fetchHits(requestModel, loadKey, AppConstants.DEFAULT_IMAGES_PER_PAGE)

            // Network issues
            if (response.errorState != null) {
                return MediatorResult.Success(
                    endOfPaginationReached = response.data.isNullOrEmpty()
                )
            }

            // Store loaded data, and next key in transaction, so that
            // they're always consistent.
            cacheDataSource.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    cacheDataSource.deleteAll()
                    cacheDataSource.deleteAllHitRemoteKeys()
                }

                // Update RemoteKey for this query.
                cacheDataSource.insertOrReplaceHitRemoteKey(
                    HitRemoteKeyModel(requestModel.query, loadKey + 1)
                )

                // Insert new users into database, which invalidates the
                // current PagingData, allowing Paging to present the updates
                // in the DB.
                if (!response.data.isNullOrEmpty()) {
                    cacheDataSource.insertAll(response.data)
                }
            }

            MediatorResult.Success(
                endOfPaginationReached = response.data.isNullOrEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}