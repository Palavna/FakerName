package com.example.yana.fakername.fragmentsViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.yana.fakername.dataClass.DocumentsUser
import com.example.yana.fakername.dataClass.SearchModel
import com.example.yana.fakername.repository.DocumentRepository
import com.example.yana.fakername.repository.SearchRepository
import com.example.yana.fakername.utils.SearchSource
import com.example.yana.fakername.utils.SingleLiveEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DetailsViewModel(private val repos: SearchRepository, private val reposUser: DocumentRepository): ViewModel() {

    val eventAuth = SingleLiveEvent<Boolean>()
    val saveDoc = MutableLiveData<DocumentsUser?>()
    val userDoc = MutableLiveData<SearchModel?>()


    fun doc(query: String, countryId: Int): Flow<PagingData<SearchModel>> {
        return Pager(
            PagingConfig(pageSize = 3, prefetchDistance = 5),
            pagingSourceFactory = { SearchSource(repos, query, countryId ) }).flow.cachedIn(viewModelScope)
    }

    fun search(text: String, id: Int?){
        viewModelScope.launch {
            kotlin.runCatching {
                val searchUser = repos.search(text,0,id)
                val user = searchUser?.data?.firstOrNull()
                if (user?.id != null){
                    val result = reposUser.documentsUser(user.id)
                    saveDoc.postValue(result)
                    userDoc.postValue(user)
                }else {
                    Log.d("wwwwwwwww", "oooooooooo")
                }
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
                val user = searchUser?.commentUser?.firstOrNull()
                if (user?.id != null){
                    val result = reposUser.documentsUser(user.id)
                }else {
                    Log.d("wwwwwwwww", "oooooooooo")
                }
                Log.d("vvvvvvvvv", "nnnnnnnnnn")
            }.onFailure {
                Log.d("vvvvvvvvv", "nnnnnnnnnn")
            }
        }
    }
}