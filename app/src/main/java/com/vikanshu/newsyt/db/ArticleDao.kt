package com.vikanshu.newsyt.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ArticleDao {

    @Insert
    suspend fun insertArticle(article: Article)
    @Delete
    suspend fun deleteArticle(article: Article)
    @Query("SELECT * FROM Article")
    suspend fun getAllArticles(): List<Article>

}