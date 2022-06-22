package com.example.yana.fakername.iteractor

import com.example.yana.fakername.dataClass.CreateComment
import com.example.yana.fakername.network.FakerService
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface CreateCommentIteractor {

    suspend fun createComment(): CreateComment?
}


class CreateCommentIteractorImpl(private val network: FakerService): CreateCommentIteractor{
    override suspend fun createComment(): CreateComment? {
        return network.createComment(inn = 1, documentId = 9, isPositive = 1, text = "dfghj")
    }

}