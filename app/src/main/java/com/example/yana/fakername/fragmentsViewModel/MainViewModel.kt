package com.example.yana.fakername.fragmentsViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yana.fakername.dataClass.Countries
import com.example.yana.fakername.repository.FakerRepository
import com.example.yana.fakername.repository.SearchRepository
import com.example.yana.fakername.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class MainViewModel(val repository: FakerRepository, val repos: SearchRepository): ViewModel() {

    init {
        loadCountriesId()
    }

    val countries = MutableLiveData <List<Countries>?>()
    val eventAuth = SingleLiveEvent<Boolean>()

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
    fun search(text: String,page: Int, id: Int?){
        viewModelScope.launch {
            kotlin.runCatching {
                val search = repos.search(text,page,id)
                eventAuth.postValue(search != null)
                Log.d("vvvvvvvvv", "nnnnnnnnnn")
            }.onFailure {
                Log.d("vvvvvvvvv", "nnnnnnnnnn")
            }
        }
    }
}