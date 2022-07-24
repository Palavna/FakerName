package com.example.yana.fakername.dataClass

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Countries (
    @PrimaryKey
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("is_active") val is_active: Int?,
    @SerializedName("created_at") val created_at: String?,
    @SerializedName("updated_at") val updated_at: String?
        )
