package com.example.yana.fakername.dataClass

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class SearchModel(

    val query: String,
    @PrimaryKey
    @SerializedName("id") val id: Int,
    @SerializedName("inn") val inn: String?,
    @SerializedName("passport") val passport: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("country_id") val country_id: Int?,
    @SerializedName("user_id") val user_id: Int?,
    @SerializedName("created_at") val created_at: String?,
    @SerializedName("updated_at") val updated_at: String?,
    @SerializedName("positiveCount") val positiveCount: Int?,
    @SerializedName("negativeCount") val negativeCount: Int?,
    @SerializedName("country") val countries: Countries?
)