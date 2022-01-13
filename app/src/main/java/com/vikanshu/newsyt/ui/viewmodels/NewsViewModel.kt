package com.vikanshu.newsyt.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vikanshu.newsyt.Constants
import com.vikanshu.newsyt.db.Article
import com.vikanshu.newsyt.db.ArticleDao
import com.vikanshu.newsyt.model.Filters
import com.vikanshu.newsyt.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    val repository: NewsRepository
) : ViewModel() {



    private val headlines_filter = MutableLiveData(
        Filters(
            "",
            "English",
            "India",
            "Breaking News"
        )
    )
    private val headlines = MutableLiveData<MutableList<Article>>(mutableListOf())
    private val search = MutableLiveData<MutableList<Article>>(mutableListOf())


    fun getHeadLines(): MutableLiveData<MutableList<Article>> {
        if (headlines.value!!.isEmpty()) {
            fetchHeadLines()
        }
        return headlines
    }

    private fun fetchHeadLines() {

    }



    fun getSearInfo() = search


    fun changeSearchQueryAndFilters() {

    }


}