package com.example.yana.fakername.network

import com.example.yana.fakername.dataClass.TokenModel

interface AuthIteractor {

    suspend fun login(login: String, password: String): TokenModel?
    suspend fun register(name: String, login: String, password: String): TokenModel?
    suspend fun logout(): Boolean
}


class AuthIteractorImpl(private val network: FakerService): AuthIteractor {

    override suspend fun login(login: String, password: String): TokenModel? {
        val map = mapOf(
            "email" to login,
            "password" to password
        )
        return network.login(map)
    }

    override suspend fun register(name: String, login: String, password: String): TokenModel? {
        val map = mapOf(
            "name" to name,
            "email" to login,
            "password" to password
        )
        return network.register(map)
    }

    override suspend fun logout(): Boolean {
        return network.logout().isSuccessful
    }

}