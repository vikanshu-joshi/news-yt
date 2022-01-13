package com.vikanshu.newsyt.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseArticles(
    @SerializedName("totalResults")
    @Expose
    var totalResults: Int,

    @SerializedName("articles")
    @Expose
    var articles: List<Article>
)
