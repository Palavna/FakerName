package com.example.yana.fakername.dataClass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Search (

    @PrimaryKey
    var idUser: String = "",
    val id: Int,
    val current_page: Int?,
    val last_page: Int?,
    val total: Int?,
    val data: List<SearchModel>

        ){
    companion object {
        fun getIdUser(countryId: Int?, query: String) = "id=$countryId&query=$query"
    }
}