package com.example.yana.fakername.network

import com.example.yana.fakername.dataClass.Documents
import com.example.yana.fakername.dataClass.DocumentsPage

interface DocumentIteractor {

    suspend fun document(page: Int): Documents?
}

class DocumentIteractorImpl(private val network: FakerService): DocumentIteractor{
    override suspend fun document(page: Int): Documents? {
        return network.documents(page)
    }

}