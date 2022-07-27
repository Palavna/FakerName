package com.example.yana.fakername.dataClass
import com.google.gson.annotations.SerializedName

data class ShowComment (

    @SerializedName("id") val id: Int,
    @SerializedName("text") val text: String,
    @SerializedName("is_positive") val is_positive: Int,
    @SerializedName("document_id") val document_id: Int,
    @SerializedName("user_id") val user_id: Int,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("updated_at") val updated_at: String,

    )