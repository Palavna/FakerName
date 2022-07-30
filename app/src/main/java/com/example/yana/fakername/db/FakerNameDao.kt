package com.example.yana.fakername.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.yana.fakername.dataClass.*

@Dao
interface FakerNameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLoadCountries(countries: List<Countries>)

    @Query("SELECT * FROM countries")
    fun getLoadCountries(): List<Countries>

    @Query("DELETE FROM countries")
    fun deleteAllLoadCountries()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProfile(profile: Profile)

    @Query("SELECT * FROM profile")
    fun getProfile(): Profile

    @Query("DELETE FROM profile")
    fun deleteAllProfile()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearch(searchModel: List<SearchModel>? )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearch(searchModel: Search?)

    @Query("SELECT * FROM search WHERE `idUser`=:id")
    fun getSearchQuery(id: String): Search?

//    @Query("SELECT * FROM search WHERE idUser=:id")
//    fun getSearch(id:String): SearchModel?

    @Query("SELECT * FROM searchModel WHERE `query`=:id")
    fun getSearchPaging(id:String): PagingSource<Int, SearchModel>

    @Query("DELETE FROM searchModel")
    fun deleteAllSearch()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchModel(searchModel: SearchModel)

    @Query("SELECT * FROM SearchModel")
    fun getSearchModel(): SearchModel?

    @Query("DELETE FROM SearchModel")
    fun deleteAllSearchModel()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountry(countries: Countries)

    @Query("SELECT * FROM countries ")
    fun getCountry(): Countries?

    @Query("DELETE FROM countries")
    fun deleteAllCountry()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDocumentsUser(documentsUser: DocumentsUser)

    @Query("SELECT * FROM documentsUser ")
    fun getDocumentsUser(): DocumentsUser?

    @Query("DELETE FROM documentsUser")
    fun deleteAllDocumentsUser()

    @Query("SELECT * FROM commentsUser WHERE `idDocument`=:idComment")
    fun getCommentUser(idComment:Int): PagingSource<Int, CommentsUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCommentsUser(commentsUser: List<CommentsUser>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCommentsUser(commentsUser: CommentsUser)

    @Query("SELECT * FROM commentsUser ")
    fun getCommentsUser(): CommentsUser?

    @Query("DELETE FROM commentsUser")
    fun deleteAllCommentsUser()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("SELECT * FROM user ")
    fun getUser(): User?

    @Query("DELETE FROM user")
    fun deleteAllUser()
}