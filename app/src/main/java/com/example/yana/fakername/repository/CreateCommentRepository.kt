package com.example.yana.fakername.repository

import com.example.yana.fakername.dataClass.CreateComment
import com.example.yana.fakername.iteractor.CreateCommentIteractor

interface CreateCommentRepository {
    suspend fun createComment(): CreateComment?
}


class CreateCommentRepositoryImpl(private val iteractor: CreateCommentIteractor): CreateCommentRepository{
    override suspend fun createComment(): CreateComment? {
        return iteractor.createComment()
    }

}