package com.example.yana.fakername.network

import androidx.paging.LoadType
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

    @FormUrlEncoded
    @POST("comments")
    suspend fun createComment(
        @Field("document_id") documentId: Int,
        @Field("inn") inn: Int,
        @Field("is_positive") isPositive: Int,
        @Field("text") text: String
    ): CreateComment?

    @DELETE("comments/{id}")
    suspend fun deleteComment(
        @Path("id") id: Int?
    ): Response<Boolean>

    @FormUrlEncoded
    @POST("documents")
    suspend fun createDocument(
        @Field("country_id") countryId: Int,
        @Field("inn") inn: String,
        @Field("is_positive") isPositive: Int,
        @Field("text") text: String
    ): CreateDocument?

    @GET("search")
    suspend fun search(
        @Query("text") text: String,
        @Query("page") page: Int?,
        @Query("country_id") country_id: Int?
    ): Search?

    @GET("documents/{id}")
    suspend fun documentsUser(
        @Path("id") id: Int,
    ): DocumentsUser?

    @GET("comments/{id}")
    suspend fun showComment(
        @Path("id") id: Int
    ): ShowComment?

}