package com.example.yana.fakername.repository

import com.example.yana.fakername.dataClass.Profile
import com.example.yana.fakername.dataClass.UpdateName
import com.example.yana.fakername.iteractor.ProfileCabinetIteractor
import com.example.yana.fakername.prefs.SharedPreferenceFaker

interface ProfileCabinetRepository {

    suspend fun profile(): Profile?
    suspend fun profileUser(name: String): UpdateName?
}


class ProfileCabinetRepositoryImpl(private val iteractor: ProfileCabinetIteractor): ProfileCabinetRepository{
    override suspend fun profile(): Profile? {
        val result = iteractor.profile()
        SharedPreferenceFaker.id = result?.id ?: -1
        return result
    }

    override suspend fun profileUser(name: String): UpdateName? {
        return iteractor.profileUser(name = name)
    }

}