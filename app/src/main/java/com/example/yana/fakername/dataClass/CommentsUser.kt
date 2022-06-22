package com.example.yana.fakername.dataClass

data class CommentsUser (

    val id: Int,
    val text: String,
    val is_positive: Int,
    val parent_id: String,
    val document_id: Int,
    val user_id: Int,
    val created_at: String,
    val updated_at: String,
    val user: User

        )


