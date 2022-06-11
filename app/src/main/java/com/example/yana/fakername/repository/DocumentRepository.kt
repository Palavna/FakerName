package com.example.yana.fakername.repository

import com.example.yana.fakername.dataClass.Documents
import com.example.yana.fakername.dataClass.DocumentsPage
import com.example.yana.fakername.db.FakerNameDao
import com.example.yana.fakername.network.DocumentIteractor

interface DocumentRepository {

//    suspend fun document(page: Int): Documents?
}

class DocumentRepositoryImpl(private val iteractor: DocumentIteractor, private val fakerDao: FakerNameDao): DocumentRepository{
//    override suspend fun document(page: Int): Documents? {
//        val result = try {
//            iteractor.document(page)
//        } catch (e: Exception) {
//            null
//        }
//        return if (result != null) {
//            fakerDao.insertDocuments(result)
//            result
//        }else{
//            fakerDao.getDocuments()
//        }
//    }


}