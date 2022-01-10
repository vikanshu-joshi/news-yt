package com.vikanshu.newsyt.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseSources(
    @SerializedName("status")
    @Expose
    var status: String,

    @SerializedName("sources")
    @Expose
    var sources: List<Source>,
)
