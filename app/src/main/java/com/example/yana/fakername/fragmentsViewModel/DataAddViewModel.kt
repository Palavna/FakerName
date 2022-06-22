package com.example.yana.fakername.fragmentsViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yana.fakername.dataClass.Countries
import com.example.yana.fakername.repository.CreateCommentRepository
import com.example.yana.fakername.repository.DocumentRepository
import com.example.yana.fakername.repository.FakerRepository
import com.example.yana.fakername.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class DataAddViewModel(val repository: FakerRepository, val reposCom: CreateCommentRepository, val reposDoc: DocumentRepository): ViewModel() {

    init {
        loadCountriesId()
    }

    val countries = MutableLiveData <List<Countries>?>()
    val eventAuth = SingleLiveEvent<Boolean>()

    fun loadCountriesId(){
        viewModelScope.launch {
            kotlin.runCatching {
                val countriesId = repository.loadCountries()
                val list = countriesId?.toMutableList()
                list?.add(0, Countries(-1, "Выберите страну", 1, "", ""))
                countries.postValue(list)
                Log.d("vvvvvvvvv", "nnnnnnnnnn")
            }.onFailure {
                Log.d("vvvvvvvvv", "nnnnnnnnnn")
            }
        }
    }

    fun createComment(){
        viewModelScope.launch {
            kotlin.runCatching {
                val createCom = reposCom.createComment()
                eventAuth.postValue(createCom != null)
                Log.d("dfghdfgh", "fghjfghjfg")
            }.onFailure {
                Log.d("dfghdfgh", "fghjfghjfg")
            }
        }
    }

    fun createDocument(countryId: Int, passport: String, comment: String, positive: Boolean) {
        viewModelScope.launch {
            kotlin.runCatching {
                val createCom = reposDoc.createDocument(countryId, passport, comment,positive)
                eventAuth.postValue(createCom != null)
                Log.d("dfghdfgh", "fghjfghjfg")
            }.onFailure {
                Log.d("dfghdfgh", "fghjfghjfg")
            }
        }
    }
}