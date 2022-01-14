package com.vikanshu.newsyt.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertArticle(article: Article)
    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("SELECT * FROM Article")
    suspend fun getAllArticles(): List<Article>


    @Query("SELECT EXISTS(SELECT * FROM Article WHERE title = :title)")
    suspend fun isArticleSaved(title: String): Boolean

}