package com.example.yana.fakername.repository

import com.example.yana.fakername.dataClass.TokenModel
import com.example.yana.fakername.network.AuthIteractor
import com.example.yana.fakername.prefs.SharedPreferenceFaker

interface AuthRepository {

    suspend fun login(login: String, password:String): TokenModel?
    suspend fun register(name: String, login: String, password:String): TokenModel?
    suspend fun logout(): Boolean
}


class AuthRepositoryImpl(private val iteractor: AuthIteractor): AuthRepository{
    override suspend fun login(login: String, password: String): TokenModel? {
        val result = iteractor.login(login = login, password = password)
        SharedPreferenceFaker.token = result?.token ?:""
        return result
    }

    override suspend fun register(name: String, login: String, password: String): TokenModel? {
        val result = iteractor.register(name = name,login = login, password = password)
        SharedPreferenceFaker.token = result?.token ?:""
        return result
    }

    override suspend fun logout():Boolean{
       val result = iteractor.logout()
        SharedPreferenceFaker.clear()
        return result

    }

}