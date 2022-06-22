package com.example.yana.fakername.repository

import com.example.yana.fakername.dataClass.CreateDocument
import com.example.yana.fakername.dataClass.DocumentsUser
import com.example.yana.fakername.db.FakerNameDao
import com.example.yana.fakername.network.DocumentIteractor

interface DocumentRepository {

//    suspend fun document(page: Int): Documents?
      suspend fun documentsUser(id: Int): DocumentsUser?
      suspend fun createDocument(
    countryId: Int,
    passport: String,
    comment: String,
    positive: Boolean
): CreateDocument?
}

class DocumentRepositoryImpl(private val iteractor: DocumentIteractor, private val fakerDao: FakerNameDao): DocumentRepository{
    override suspend fun documentsUser(id: Int): DocumentsUser? {
        return iteractor.documentsUser(id)
    }

    override suspend fun createDocument(
        countryId: Int,
        passport: String,
        comment: String,
        positive: Boolean
    ): CreateDocument? {
        return iteractor.createDocument(countryId, passport, comment, positive)
    }
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