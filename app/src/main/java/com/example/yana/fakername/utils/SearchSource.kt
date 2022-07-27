package com.example.yana.fakername.utils

import androidx.paging.*
import androidx.room.withTransaction
import com.example.yana.fakername.dataClass.SearchModel
import com.example.yana.fakername.db.FakerAppDataBase
import com.example.yana.fakername.iteractor.SearchIteractor
import com.example.yana.fakername.network.FakerService
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class SearchSource(
    private val query: String,
    private val database: FakerAppDataBase,
    private val networkService: FakerService,
    private val countryId: Int

    ): RemoteMediator<Int, SearchModel>() {
    val userDao = database.getFakerDao()


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, SearchModel>
    ): MediatorResult {

        return try {

            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }
                    lastItem.id
                }
            }

            val response = networkService.search(
                query, loadKey, loadKey
            )

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    userDao.deleteAllSearch()
                }
                val list = response?.data?.map {
                    SearchModel(id = it.id, inn = it.inn, passport = it.passport, description = it.description,
                    country_id = it.country_id, user_id = it.user_id, created_at = it.created_at, updated_at = it.updated_at,
                    positiveCount = it.positiveCount, negativeCount = it.negativeCount, countries = it.countries,
                    query = "id=$countryId&query=$query")
                }
                userDao.insertSearch(list)
            }

            MediatorResult.Success(
                endOfPaginationReached = response?.id == null
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
    }

    //    override fun getRefreshKey(state: PagingState<Int, SearchModel>): Int? {
//        return null
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchModel> {
//        val page = params.key ?: 1
//        return try {
//            val response = iteractor.search(query,page, countryId)
//            val data = response?.data?: emptyList()
//            val nextKey = if (data.isNullOrEmpty()) {
//                null
//            }else {
//                page + 1
//            }
//
//            LoadResult.Page(data, if (page == 1) null else page - 1, nextKey)
//
//        } catch (e: IOException) {
//            return LoadResult.Error(e)
//        } catch (e: HttpException) {
//            return LoadResult.Error(e)
//        }
//    }


