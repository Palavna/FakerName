package com.example.yana.fakername.fragmentsViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.yana.fakername.dataClass.DocumentsPage
import com.example.yana.fakername.dataClass.Profile
import com.example.yana.fakername.dataClass.UpdateName
import com.example.yana.fakername.network.DocumentIteractor
import com.example.yana.fakername.prefs.SharedPreferenceFaker
import com.example.yana.fakername.repository.AuthRepository
import com.example.yana.fakername.repository.ProfileCabinetRepository
import com.example.yana.fakername.utils.DocumentsSource
import com.example.yana.fakername.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PrivateCabinetViewModel(private val authRepos: AuthRepository, private val profRepos: ProfileCabinetRepository,
                              val iteractor: DocumentIteractor): ViewModel() {

    val eventAuth = SingleLiveEvent<Boolean>()
    val profile = MutableLiveData<Profile?>()
    val progress = MutableLiveData(false)
    val profileUser = MutableLiveData<UpdateName?>()

    init {
        profile()
        profileUser(name = String())
    }

    fun logout(){
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val result = authRepos.logout()
                eventAuth.postValue(result != null)
                Log.d("dfghdfgh", "fghjfghjfg")
            }.onFailure {
                eventAuth.postValue(false)
                Log.d("dfghdfgh", "fghjfghjfg")
            }
        }
    }

    fun profile(){
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                progress.postValue(true)
                val result = profRepos.profile()
                profile.postValue(result)
                progress.postValue(false)
                Log.d("dfghdfgh", "fghjfghjfg")
            }.onFailure {
                eventAuth.postValue(false)
                progress.postValue(false)
                Log.d("dfghdfgh", "fghjfghjfg")
            }
        }
    }

    fun profileUser(name: String){
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val result = profRepos.profileUser(name = name)
                val profile = profRepos.profile()
                SharedPreferenceFaker.id = profile?.id ?: -1
                profileUser.postValue(result)
                profile()
                Log.d("dfghdfgh", "fghjfghjfg")
            }.onFailure {
                Log.d("dfghdfgh", "fghjfghjfg")
            }
        }
    }

    fun doc(): Flow<PagingData<DocumentsPage>> {
        return Pager(
            PagingConfig(pageSize = 3, prefetchDistance = 5),
            pagingSourceFactory = { DocumentsSource(iteractor) }).flow.cachedIn(viewModelScope)
    }
}