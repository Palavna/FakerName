package com.example.yana.fakername.fragmentsViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.yana.fakername.dataClass.SearchModel
import com.example.yana.fakername.repository.DocumentRepository
import com.example.yana.fakername.repository.SearchRepository
import com.example.yana.fakername.utils.SearchSource
import com.example.yana.fakername.utils.SingleLiveEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DetailsViewModel(private val repos: SearchRepository, private val reposUser: DocumentRepository): ViewModel() {

    val eventAuth = SingleLiveEvent<Boolean>()

    fun doc(query: String, countryId: Int): Flow<PagingData<SearchModel>> {
        return Pager(
            PagingConfig(pageSize = 3, prefetchDistance = 5),
            pagingSourceFactory = { SearchSource(repos, query, countryId ) }).flow.cachedIn(viewModelScope)
    }

    fun searchUser(text: String,page: Int, id: Int?){
        viewModelScope.launch {
            kotlin.runCatching {
                val searchUser = repos.searchUser(text,page,id)
                eventAuth.postValue(searchUser != null)
                Log.d("vvvvvvvvv", "nnnnnnnnnn")
            }.onFailure {
                Log.d("vvvvvvvvv", "nnnnnnnnnn")
            }
        }
    }

    fun documentsUser(id: Int){
        viewModelScope.launch {
            kotlin.runCatching {
                val searchUser = reposUser.documentsUser(id)
                eventAuth.postValue(searchUser != null)
                Log.d("vvvvvvvvv", "nnnnnnnnnn")
            }.onFailure {
                Log.d("vvvvvvvvv", "nnnnnnnnnn")
            }
        }
    }
}