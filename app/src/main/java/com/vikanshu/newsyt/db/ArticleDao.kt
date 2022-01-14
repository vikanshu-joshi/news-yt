package com.vikanshu.newsyt.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article)

    @Query("DELETE FROM Article WHERE title = :title")
    suspend fun deleteArticle(title: String)

    @Query("SELECT * FROM Article")
    suspend fun getAllArticles(): List<Article>


    @Query("SELECT EXISTS(SELECT * FROM Article WHERE title = :title)")
    suspend fun isArticleSaved(title: String): Boolean

}