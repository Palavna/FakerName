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
import com.example.yana.fakername.dataClass.ShowComment
import com.example.yana.fakername.repository.CreateCommentRepository
import com.example.yana.fakername.repository.DocumentRepository
import com.example.yana.fakername.repository.SearchRepository
import com.example.yana.fakername.utils.SearchSource
import com.example.yana.fakername.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DetailsViewModel(private val repos: SearchRepository, private val reposUser: DocumentRepository,
                       private val reposDoc: DocumentRepository): ViewModel() {

    val eventAuth = SingleLiveEvent<Boolean>()
    val saveDoc = MutableLiveData<DocumentsUser?>()
    val userDoc = MutableLiveData<SearchModel?>()
    val progress = MutableLiveData(false)


    fun doc(query: String, countryId: Int): Flow<PagingData<SearchModel>> {
        return Pager(
            PagingConfig(pageSize = 3, prefetchDistance = 5),
            pagingSourceFactory = { SearchSource(repos, query, countryId ) }).flow.cachedIn(viewModelScope)
    }

    fun search(text: String, id: Int?){
        viewModelScope.launch(Dispatchers.IO) {
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
    fun createDocument(countryId: Int, passport: String, comment: String, positive: Boolean) {
        viewModelScope.launch {
            kotlin.runCatching {
                val createCom = reposDoc.createDocument(countryId, passport, comment,positive)
                eventAuth.postValue(createCom != null)
                search(passport,countryId)
                progress.postValue(false)
                Log.d("dfghdfgh", "fghjfghjfg")
            }.onFailure {
                Log.d("dfghdfgh", "fghjfghjfg")
            }
        }
    }
}