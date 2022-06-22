package com.example.yana.fakername.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.yana.fakername.dataClass.Countries
import com.example.yana.fakername.dataClass.DocumentsPage
import com.example.yana.fakername.dataClass.SearchModel

@Database(entities = [Countries::class, DocumentsPage::class], version = 1)
abstract class FakerAppDataBase: RoomDatabase() {
    abstract fun getFakerDao(): FakerNameDao
}