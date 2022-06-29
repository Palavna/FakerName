package com.example.yana.fakername.iteractor

import com.example.yana.fakername.dataClass.Search
import com.example.yana.fakername.network.FakerService

interface SearchIteractor {
    suspend fun search(text: String, page: Int, id: Int?): Search?
}

class SearchIteractorImpl(private val network: FakerService): SearchIteractor{
    override suspend fun search(text: String, page: Int, id: Int?): Search? {
        return network.search(text, page, id)
    }
}