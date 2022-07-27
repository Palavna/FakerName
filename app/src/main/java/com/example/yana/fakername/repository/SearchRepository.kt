package com.example.yana.fakername.repository

import android.util.Log
import com.example.yana.fakername.dataClass.Search
import com.example.yana.fakername.db.FakerNameDao
import com.example.yana.fakername.iteractor.SearchIteractor

interface SearchRepository {
    suspend fun search(text: String, page: Int, id: Int?): Search?
}


class SearchRepositoryImpl(private val iteractor: SearchIteractor, private val fakerDao: FakerNameDao): SearchRepository{
    override suspend fun search(text: String, page: Int, id: Int?): Search? {
       val result = try {
            val result =  iteractor.search(text, page, id)
            result
        }catch (e:Exception){
            null
        }
        val idU = "countryid=$id&query=$text"
        result?.idUser = idU
        if (result != null){
            fakerDao.insertSearch(result)
        }
        val newResult = fakerDao.getSearch(idU)
       Log.d("vbnmbnbn","vvvvvvvvv")
        return newResult


    }

}