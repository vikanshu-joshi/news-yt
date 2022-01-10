package com.vikanshu.newsyt.repository

import com.vikanshu.newsyt.api.NewsApiRequest
import com.vikanshu.newsyt.db.ArticleDao
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    val articleDao: ArticleDao,
    val API: NewsApiRequest
): NewsRepository {
    override suspend fun getArticles() {
        TODO("Not yet implemented")
    }
}