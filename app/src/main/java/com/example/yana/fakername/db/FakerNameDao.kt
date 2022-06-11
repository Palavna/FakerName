package com.example.yana.fakername.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.yana.fakername.dataClass.Countries
import com.example.yana.fakername.dataClass.Documents
import com.example.yana.fakername.dataClass.DocumentsPage

@Dao
interface FakerNameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLoadCountries(countries: List<Countries>)

    @Query("SELECT * FROM countries")
    fun getLoadCountries(): List<Countries>

    @Query("DELETE FROM countries")
    fun deleteAllLoadCountries()

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertDocuments(documents: Documents)
//
//    @Query("SELECT * FROM documentspage")
//    fun getDocuments(): Documents
//
//    @Query("DELETE FROM documentspage")
//    fun deleteAllDocuments()
}