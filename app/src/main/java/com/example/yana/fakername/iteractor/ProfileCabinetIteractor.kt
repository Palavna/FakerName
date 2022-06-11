package com.example.yana.fakername.iteractor

import com.example.yana.fakername.dataClass.Profile
import com.example.yana.fakername.dataClass.UpdateName
import com.example.yana.fakername.network.FakerService

interface ProfileCabinetIteractor {

    suspend fun profile(): Profile?
    suspend fun profileUser(name: String): UpdateName?
}

class ProfileCabinetIteractorImpl(private val network: FakerService): ProfileCabinetIteractor{
    override suspend fun profile(): Profile? {
        return network.profile()
    }

    override suspend fun profileUser(name: String): UpdateName? {
        val map = mapOf(
            "name" to name)
        return network.profileUser(map)
    }

}