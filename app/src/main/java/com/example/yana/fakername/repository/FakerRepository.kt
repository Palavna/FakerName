package com.example.yana.fakername.repository

import com.example.yana.fakername.dataClass.Countries
import com.example.yana.fakername.db.FakerNameDao
import com.example.yana.fakername.network.FakerIteractor

interface FakerRepository {

    suspend fun loadCountries(): List<Countries>?
}


class FakerRepositoryImpl(private val iteractor: FakerIteractor, private val fakerDao: FakerNameDao): FakerRepository{

    override suspend fun loadCountries(): List<Countries>? {
        val result = try {
            iteractor.loadCountries()
        } catch (e: Exception) {
            null
        }
        return if (result != null) {
            fakerDao.insertLoadCountries(result)
            result
        }else{
            fakerDao.getLoadCountries()
        }
    }

}