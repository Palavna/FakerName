package com.example.yana.fakername.dataClass

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class CommentsUser (

    val idDocument: Int,
    @PrimaryKey
    @SerializedName("id") val id: Int,
    @SerializedName("text") val text: String,
    @SerializedName("is_positive") val is_positive: Int,
    @SerializedName("parent_id") val parent_id: String,
    @SerializedName("document_id") val document_id: Int,
    @SerializedName("user_id") val user_id: Int,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("updated_at") val updated_at: String,
    @SerializedName("user") val user: User

        )


