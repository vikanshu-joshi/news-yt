package com.vikanshu.newsyt.repository

import com.vikanshu.newsyt.Constants
import com.vikanshu.newsyt.api.NewsApiRequest
import com.vikanshu.newsyt.db.ArticleDao
import com.vikanshu.newsyt.model.ResponseArticles
import javax.inject.Inject

class NewsRepository @Inject constructor(
    val articleDao: ArticleDao,
    val API: NewsApiRequest
) {


    suspend fun getArticles(
        category: String,
        language: String,
        country: String,
        page: Int
    ): ResponseArticles? {
        val response = API.getHeadLines(
            Constants.CATEGORIES[category]!!,
            Constants.LANGUAGES[language]!!,
            Constants.COUNTRIES[country]!!,
            page
        )
        return response.body()
    }


    suspend fun searchArticles(
        query: String,
        topic: String,
        page: Int,
        language: String,
        country: String,
        sortBy: String,
        from: String,
        to: String
    ): ResponseArticles? {
        val response = API.searchArticles(
            query,
            "",
            Constants.LANGUAGES[language]!!,
            "",
            page,
            sortBy,
            from,
            to
        )
        return response.body()
    }

}