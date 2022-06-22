package com.example.yana.fakername.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.yana.fakername.dataClass.SearchModel
import com.example.yana.fakername.repository.SearchRepository
import retrofit2.HttpException
import java.io.IOException

class SearchSource(
    private val iteractor: SearchRepository,
    private val query: String,
    private val countryId: Int
): PagingSource<Int, SearchModel>() {

    override fun getRefreshKey(state: PagingState<Int, SearchModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchModel> {
        val page = params.key ?: 1
        return try {
            val response = iteractor.search(query,page, countryId)
            val data = response?.data?: emptyList()
            val nextKey = if (data.isNullOrEmpty()) {
                null
            }else {
                page + 1
            }

            LoadResult.Page(data, if (page == 1) null else page - 1, nextKey)

        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}