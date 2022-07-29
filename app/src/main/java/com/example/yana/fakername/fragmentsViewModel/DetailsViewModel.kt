package com.example.yana.fakername.fragmentsViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.yana.fakername.dataClass.DocumentsUser
import com.example.yana.fakername.dataClass.SearchModel
import com.example.yana.fakername.db.FakerAppDataBase
import com.example.yana.fakername.network.FakerService
import com.example.yana.fakername.repository.DocumentRepository
import com.example.yana.fakername.repository.SearchRepository
import com.example.yana.fakername.utils.SearchSource
import com.example.yana.fakername.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repos: SearchRepository, private val reposUser: DocumentRepository,
    private val reposDoc: DocumentRepository, private val database: FakerAppDataBase,
    private val networkService: FakerService
) : ViewModel() {

    val eventAuth = SingleLiveEvent<Boolean>()
    val saveDoc = MutableLiveData<DocumentsUser?>()
    val userDoc = MutableLiveData<SearchModel?>()
    val progress = MutableLiveData(false)


//    @OptIn(ExperimentalPagingApi::class)
//    fun doc(query: String, countryId: Int): Flow<PagingData<SearchModel>> {
//        val pagingSourceFactory =
//            { database.getFakerDao().getSearchPaging("id=$countryId&query=$query") }
//        return Pager(
//            config = PagingConfig(pageSize = 50, enablePlaceholders = false),
//            remoteMediator = SearchSource(
//                query, database, networkService, countryId
//            ),
//            pagingSourceFactory = pagingSourceFactory
//        ).flow
//    }

    fun search(text: String, id: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                progress.postValue(true)
                val searchUser = repos.search(text, 0, id)
                val user = searchUser?.data?.firstOrNull()
                progress.postValue(true)
                if (user?.id != null) {
                    val result = reposUser.documentsUser(user.id)
                    saveDoc.postValue(result)
                    progress.postValue(false)
                } else {
                    Log.d("wwwwwwwww", "oooooooooo")
                }
                userDoc.postValue(user)
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
                progress.postValue(true)
                val createCom = reposDoc.createDocument(countryId, passport, comment, positive)
                eventAuth.postValue(createCom != null)
                progress.postValue(true)
                search(passport, countryId)
                progress.postValue(false)
                Log.d("dfghdfgh", "fghjfghjfg")
            }.onFailure {
                Log.d("dfghdfgh", "fghjfghjfg")
            }
        }
    }
}