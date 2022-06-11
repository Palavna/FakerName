package com.example.yana.fakername.network

import com.example.yana.fakername.dataClass.*
import retrofit2.Response
import retrofit2.http.*

interface FakerService {

    @GET("countries")
    suspend fun loadCountries(
    ): List<Countries>?

    @POST("sign-in")
    suspend fun login(
        @Body model: Map<String, String>
    ): TokenModel?

    @POST("register")
    suspend fun register(
        @Body model: Map<String, String>
    ): TokenModel?

    @POST("logout")
    suspend fun logout(): Response<Unit>

    @GET("documents")
    suspend fun documents(
        @Query("page") page: Int
    ): Documents?

    @GET("profile")
    suspend fun profile(
    ): Profile?

    @PUT("profile")
    suspend fun profileUser(
        @Body model: Map<String, String>
    ): UpdateName?

    @Multipart
    @POST("comments")
    suspend fun createComment(
        @Part inn: Int,
        @Part is_positive: Boolean,
        @Part text: String
    ): CreateComment?

}