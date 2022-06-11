package com.example.yana.fakername.fragmentsViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yana.fakername.dataClass.Countries
import com.example.yana.fakername.repository.FakerRepository
import kotlinx.coroutines.launch

class MainViewModel(val repository: FakerRepository): ViewModel() {

    init {
        loadCountriesId()
    }

    val countries = MutableLiveData <List<Countries>?>()

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
}