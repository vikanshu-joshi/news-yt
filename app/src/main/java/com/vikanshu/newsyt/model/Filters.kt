package com.vikanshu.newsyt.model

data class Filters(
    var query: String,
    var topic: String,
    var page: Int,
    var sortBy: String,
    var from: String,
    var to: String
)