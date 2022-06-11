package com.example.yana.fakername.network

import com.example.yana.fakername.dataClass.Countries

interface FakerIteractor {

    suspend fun loadCountries(): List<Countries>?
}


class FakerIteractorImpl(private val network: FakerService): FakerIteractor{

    override suspend fun loadCountries(): List<Countries>? {
        return network.loadCountries()
    }

}