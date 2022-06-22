package com.example.yana.fakername.network

import com.example.yana.fakername.dataClass.CreateDocument
import com.example.yana.fakername.dataClass.Documents
import com.example.yana.fakername.dataClass.DocumentsUser

interface DocumentIteractor {

    suspend fun document(page: Int): Documents?
    suspend fun documentsUser(id: Int): DocumentsUser?
    suspend fun createDocument(
        countryId: Int,
        passport: String,
        comment: String,
        positive: Boolean
    ): CreateDocument?

}

class DocumentIteractorImpl(private val network: FakerService): DocumentIteractor{
    override suspend fun document(page: Int): Documents? {
        return network.documents(page)
    }

    override suspend fun documentsUser(id: Int): DocumentsUser? {
        return network.documentsUser(id)
    }

    override suspend fun createDocument(
        countryId: Int,
        passport: String,
        comment: String,
        positive: Boolean
    ): CreateDocument? {
        val isPositive = if (positive) 1 else 0
        return network.createDocument(inn = passport, countryId = countryId, isPositive = isPositive, text = comment)
    }

}