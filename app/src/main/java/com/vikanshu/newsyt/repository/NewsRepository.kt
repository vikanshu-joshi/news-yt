package com.vikanshu.newsyt.repository

import com.vikanshu.newsyt.Constants
import com.vikanshu.newsyt.api.NewsApiRequest
import com.vikanshu.newsyt.db.ArticleDao
import com.vikanshu.newsyt.model.ResponseArticles
import com.vikanshu.newsyt.utility.NewsUtility
import timber.log.Timber
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
        category: String,
        page: Int,
        language: String,
        country: String,
        sortBy: String,
        from: String,
        to: String
    ): ResponseArticles? {
        Timber.e(
            query,
            Constants.CATEGORIES[category]!!,
            Constants.LANGUAGES[language]!!,
            "India",
            page,
            Constants.SORTBY[sortBy]!!,
            NewsUtility.formatSearchNewsDate(from),
            NewsUtility.formatSearchNewsDate(to)
        )
        val response = API.searchArticles(
            query,
            Constants.CATEGORIES[category]!!,
            Constants.LANGUAGES[language]!!,
            "India",
            page,
            Constants.SORTBY[sortBy]!!,
            NewsUtility.formatSearchNewsDate(from),
            NewsUtility.formatSearchNewsDate(to)
        )
        return response.body()
    }

}