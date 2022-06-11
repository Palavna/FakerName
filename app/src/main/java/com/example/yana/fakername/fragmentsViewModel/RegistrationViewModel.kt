package com.example.yana.fakername.fragmentsViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yana.fakername.prefs.SharedPreferenceFaker
import com.example.yana.fakername.repository.AuthRepository
import com.example.yana.fakername.repository.ProfileCabinetRepository
import com.example.yana.fakername.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel(private val authRepos: AuthRepository, private val profRepos: ProfileCabinetRepository): ViewModel() {

    val eventAuth = SingleLiveEvent<Boolean>()

    fun login(email: String, password: String){
       viewModelScope.launch(Dispatchers.IO) {
           runCatching {
               val result = authRepos.login(login = email, password = password)
               val profile = profRepos.profile()
               SharedPreferenceFaker.id = profile?.id ?: -1
               eventAuth.postValue(result != null)
               Log.d("dfghdfgh", "fghjfghjfg")
           }.onFailure {
               eventAuth.postValue(false)
               Log.d("dfghdfgh", "fghjfghjfg")
           }
       }
    }
}