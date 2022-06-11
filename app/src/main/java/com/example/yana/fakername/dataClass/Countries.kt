package com.example.yana.fakername.dataClass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Countries (
    @PrimaryKey
    val id: Int,
    val name: String,
    val is_active: Int,
    val created_at: String,
    val updated_at: String
        )
