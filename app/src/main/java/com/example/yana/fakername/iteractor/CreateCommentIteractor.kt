package com.example.yana.fakername.iteractor

import com.example.yana.fakername.dataClass.CreateComment
import com.example.yana.fakername.dataClass.ShowComment
import com.example.yana.fakername.network.FakerService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

interface CreateCommentIteractor {

    suspend fun createComment(): CreateComment?
    suspend fun showComment(id: Int): ShowComment?
    suspend fun deleteComment(id: Int?): Response<Boolean>
}


class CreateCommentIteractorImpl(private val network: FakerService): CreateCommentIteractor{
    override suspend fun createComment(): CreateComment? {
        return network.createComment(inn = 1, documentId = 9, isPositive = 1, text = "dfghj")
    }

    override suspend fun showComment(id: Int): ShowComment? {
        return network.showComment(id)
    }

    override suspend fun deleteComment(id: Int?): Response<Boolean>{
        return network.deleteComment(id)
    }

}