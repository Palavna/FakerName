package com.example.yana.fakername.fragmentsViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.yana.fakername.dataClass.Countries
import com.example.yana.fakername.repository.DocumentRepository
import com.example.yana.fakername.repository.FakerRepository
import com.example.yana.fakername.repository.SearchRepository
import com.example.yana.fakername.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class MainViewModel(val repository: FakerRepository, val repos: SearchRepository, private val reposUser: DocumentRepository): ViewModel() {

    init {
        loadCountriesId()
    }

    lateinit var navController: NavController


    val countries = MutableLiveData <List<Countries>?>()
    val eventAuth = SingleLiveEvent<Boolean>()
    val countryPos = MutableLiveData<Int?>()

    fun loadCountriesId() {
        viewModelScope.launch {
            kotlin.runCatching {
                val countriesId = repository.loadCountries()
                 val list = countriesId?.toMutableList()
                list?.add(0, Countries(-1, "Выберите страну", 1, "", ""))
                this@MainViewModel.countries.postValue(list)
                Log.d("vvvvvvvvv", "nnnnnnnnnn")
            }.onFailure {
                Log.d("vvvvvvvvv", "nnnnnnnnnn")
            }
        }
    }

    fun search(text: String, id: Int?){
        viewModelScope.launch {
            kotlin.runCatching {
                val search = repos.search(text,0, id)
                val user = search?.data?.firstOrNull()
                if (user?.id != null){
                    val result = reposUser.documentsUser(user.id)
                }else {
                    Log.d("vvvvvvvvv", "nnnnnnnnnn")
                }
                eventAuth.postValue(search != null)
                Log.d("vvvvvvvvv", "nnnnnnnnnn")
            }.onFailure {
                Log.d("vvvvvvvvv", "nnnnnnnnnn")
            }
        }
    }
}