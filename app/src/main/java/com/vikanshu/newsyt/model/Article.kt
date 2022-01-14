package com.vikanshu.newsyt.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("title")
    @Expose
    var title: String,

    @SerializedName("description")
    @Expose
    var description: String,

    @SerializedName("content")
    @Expose
    var content: String,

    @SerializedName("url")
    @Expose
    var url: String,

    @SerializedName("image")
    @Expose
    var image: String,

    @SerializedName("publishedAt")
    @Expose
    var publishedAt: String,

    @SerializedName("source")
    @Expose
    var source: Source,
) {
}
