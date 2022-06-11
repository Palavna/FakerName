package com.example.yana.fakername.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.yana.fakername.dataClass.DocumentsPage
import com.example.yana.fakername.network.DocumentIteractor
import retrofit2.HttpException
import java.io.IOException

class DocumentsSource
    (private val iteractor: DocumentIteractor): PagingSource<Int, DocumentsPage>() {
    override fun getRefreshKey(state: PagingState<Int, DocumentsPage>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DocumentsPage> {
        val page = params.key ?: 1
        return try {
            val response = iteractor.document(page)
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