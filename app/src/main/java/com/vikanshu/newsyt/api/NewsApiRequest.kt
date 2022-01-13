package com.vikanshu.newsyt.api

import com.vikanshu.newsyt.model.ResponseArticles
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiRequest {

    @GET("top-headlines")
    suspend fun getHeadLines(
        @Query("topic") topic: String,
        @Query("lang") lang: String,
        @Query("country") country: String,
        @Query("page") page: Int
    ): Response<ResponseArticles>

    @GET("search/?in=title")
    suspend fun searchArticles(
        @Query("query") query: String,
        @Query("topic") topic: String,
        @Query("lang") lang: String,
        @Query("country") country: String,
        @Query("page") page: String,
        @Query("sortby") sortBy: String, // relevance, publishedAt
        @Query("from") from: String, // 2022-01-13T04:05:40Z
        @Query("to") to: String, // 2022-01-13T04:05:40Z
    ): Response<ResponseArticles>
}