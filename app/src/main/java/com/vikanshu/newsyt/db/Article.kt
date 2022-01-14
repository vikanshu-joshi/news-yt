package com.vikanshu.newsyt.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Article(
    @ColumnInfo(name = "source")
    var source: String,
    @ColumnInfo(name = "title")

    @PrimaryKey(autoGenerate = false)
    var title: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "url")
    var url: String,
    @ColumnInfo(name = "image")
    var urlToImage: String,
    @ColumnInfo(name = "publishedAt")
    var publishedAt: String,
    @ColumnInfo(name = "content")
    var content: String
)
