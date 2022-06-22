package com.example.yana.fakername.dataClass

data class Search (
    val current_page: Int,
    val last_page: Int,
    val total: Int,
    val data: List<SearchModel>

        )