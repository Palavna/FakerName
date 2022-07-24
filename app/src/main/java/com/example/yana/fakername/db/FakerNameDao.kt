package com.example.yana.fakername.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.yana.fakername.dataClass.*

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearch(search: Search)

    @Query("SELECT * FROM search")
    fun getSearch(): Search?

    @Query("DELETE FROM search")
    fun deleteAllSearch()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchModel(searchModel: SearchModel)

    @Query("SELECT * FROM SearchModel")
    fun getSearchModel(): SearchModel?

    @Query("DELETE FROM SearchModel")
    fun deleteAllSearchModel()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountry(countries: Countries)

    @Query("SELECT * FROM countries ")
    fun getCountry(): Countries?

    @Query("DELETE FROM countries")
    fun deleteAllCountry()
}