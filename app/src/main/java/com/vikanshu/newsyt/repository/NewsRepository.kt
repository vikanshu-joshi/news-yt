package com.vikanshu.newsyt.repository

import com.vikanshu.newsyt.api.NewsApiRequest
import com.vikanshu.newsyt.db.ArticleDao
import javax.inject.Inject

class NewsRepository @Inject constructor(
    val articleDao: ArticleDao,
    val API: NewsApiRequest
) {


    suspend fun getArticles() {

    }


}