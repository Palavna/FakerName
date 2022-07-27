package com.example.yana.fakername.dataClass

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User (

    @PrimaryKey
    @SerializedName("id") val id: Int,
    @SerializedName("role_id") val role_id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: Int?,
    @SerializedName("avatar") val avatar: String,
    @SerializedName("email_verified_at") val email_verified_at: String,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("updated_at") val updated_at: String

        )