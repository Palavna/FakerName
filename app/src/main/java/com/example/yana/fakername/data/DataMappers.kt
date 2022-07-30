package com.example.yana.fakername.data

import com.example.yana.fakername.dataClass.Search

fun Search.toSearchWithId(countryId: Int?, query: String): Search {
    return Search(
        idUser = Search.getIdUser(countryId, query),
        id = this.id,
        current_page = this.current_page,
        last_page = this.last_page,
        total = this.total,
        data = this.data
    )
}