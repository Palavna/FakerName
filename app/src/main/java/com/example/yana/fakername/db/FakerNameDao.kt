package com.example.yana.fakername.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.yana.fakername.dataClass.Countries
import com.example.yana.fakername.dataClass.Documents
import com.example.yana.fakername.dataClass.DocumentsPage
import com.example.yana.fakername.dataClass.Profile

@Dao
interface FakerNameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLoadCountries(countries: List<Countries>)

    @Query("SELECT * FROM countries")
    fun getLoadCountries(): List<Countries>

    @Query("DELETE FROM countries")
    fun deleteAllLoadCountries()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProfile(profile: Profile)

    @Query("SELECT * FROM profile")
    fun getProfile(): Profile

    @Query("DELETE FROM profile")
    fun deleteAllProfile()
}