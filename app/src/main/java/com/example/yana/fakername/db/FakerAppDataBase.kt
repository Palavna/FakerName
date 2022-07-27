package com.example.yana.fakername.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.yana.fakername.dataClass.*
import com.example.yana.fakername.utils.TypeConventers

@Database(entities = [Countries::class, DocumentsPage::class, Profile::class, Search::class,
                     SearchModel::class, DocumentsUser::class, CommentsUser::class,
                     User::class], version = 1)
@TypeConverters(TypeConventers::class)
abstract class FakerAppDataBase: RoomDatabase() {
    abstract fun getFakerDao(): FakerNameDao
}