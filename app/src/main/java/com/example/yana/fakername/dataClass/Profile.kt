package com.example.yana.fakername.dataClass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profile (

    @PrimaryKey
    val id: Int,
    val role_id: Int,
    val name: String,
    val email: String,
    val phone: Int?,
    val avatar: String,
    val email_verified_at: String?,
    val created_at: String,
    val updated_at: String

        )
