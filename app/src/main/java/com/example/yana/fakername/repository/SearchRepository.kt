package com.example.yana.fakername.repository

import com.example.yana.fakername.dataClass.Search
import com.example.yana.fakername.iteractor.SearchIteractor

interface SearchRepository {
    suspend fun search(text: String, page: Int, id: Int?): Search?
}


class SearchRepositoryImpl(private val iteractor: SearchIteractor): SearchRepository{
    override suspend fun search(text: String, page: Int, id: Int?): Search? {
        return iteractor.search(text, page, id)
    }

}