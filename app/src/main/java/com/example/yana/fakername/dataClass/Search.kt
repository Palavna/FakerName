package com.example.yana.fakername.dataClass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Search (

    @PrimaryKey
    val id: Int,
    val current_page: Int?,
    val last_page: Int?,
    val total: Int?,
    val data: List<SearchModel>

        )