package com.example.yana.fakername.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.yana.fakername.dataClass.Countries
import com.example.yana.fakername.dataClass.ListCountries

@Dao
interface FakerNameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLoadCountries(countries: List<Countries>)

    @Query("SELECT * FROM countries")
    fun getLoadCountries(): List<Countries>

    @Query("DELETE FROM countries")
    fun deleteAllLoadCountries()
}