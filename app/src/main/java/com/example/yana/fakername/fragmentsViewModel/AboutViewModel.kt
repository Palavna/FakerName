package com.example.yana.fakername.fragmentsViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.yana.fakername.dataClass.DocumentsPage
import com.example.yana.fakername.network.DocumentIteractor
import com.example.yana.fakername.repository.DocumentRepository
import com.example.yana.fakername.utils.DocumentsSource
import kotlinx.coroutines.flow.Flow

class AboutViewModel(val repository: DocumentRepository, val iteractor: DocumentIteractor) : ViewModel() {

    init {
        loadDocuments()
    }

    val document = MutableLiveData<List<DocumentsPage>?>()

    fun loadDocuments() {
//        viewModelScope.launch {
//            kotlin.runCatching {
//                val documents = repository.document(1)
//                val list = documents?.toMutableList()
//                list?.add(0, DocumentsPage(-1, "Выберите страну", "", "", 1, 1, "", ""))
//                document.postValue(list)
//                Log.d("vvvvvvvvv", "nnnnnnnnnn")
//            }.onFailure {
//                Log.d("vvvvvvvvv", "nnnnnnnnnn")
//            }
//        }
    }

    fun doc(): Flow<PagingData<DocumentsPage>> {
        return Pager(
            PagingConfig(pageSize = 3, prefetchDistance = 5),
            pagingSourceFactory = { DocumentsSource(iteractor) }).flow.cachedIn(viewModelScope)
    }
}