package com.example.yana.fakername.dataClass

import com.google.gson.annotations.SerializedName

data class CreateDocument (

    @SerializedName("id") val id: Int,
    @SerializedName("inn") val inn: String,
    @SerializedName("passport") val passport: String,
    @SerializedName("description") val description: String,
    @SerializedName("country_id") val country_id: Int,
    @SerializedName("user_id") val user_id: Int,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("updated_at") val updated_at: String,
    @SerializedName("comments") val comments: List<Comments>

        )

