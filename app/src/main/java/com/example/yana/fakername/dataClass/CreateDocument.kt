package com.example.yana.fakername.dataClass

data class CreateDocument (

    val id: Int,
    val inn: String,
    val passport: String,
    val description: String,
    val country_id: Int,
    val user_id: Int,
    val created_at: String,
    val updated_at: String,
    val comments: List<Comments>

        )

