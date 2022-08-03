package com.example.yana.fakername.repository

import com.example.yana.fakername.dataClass.CreateComment
import com.example.yana.fakername.dataClass.ShowComment
import com.example.yana.fakername.iteractor.CreateCommentIteractor
import retrofit2.Response

interface CreateCommentRepository {
    suspend fun createComment(): CreateComment?
    suspend fun showComment(id: Int): ShowComment?
    suspend fun deleteComment(id: Int?): Response<Boolean>
}


class CreateCommentRepositoryImpl(private val iteractor: CreateCommentIteractor): CreateCommentRepository{
    override suspend fun createComment(): CreateComment? {
        return iteractor.createComment()
    }

    override suspend fun showComment(id: Int): ShowComment? {
        return iteractor.showComment(id)
    }

    override suspend fun deleteComment(id: Int?): Response<Boolean> {
        return iteractor.deleteComment(id)
    }

}