package com.vikanshu.newsyt.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikanshu.newsyt.Constants
import com.vikanshu.newsyt.model.Article
import com.vikanshu.newsyt.model.Filters
import com.vikanshu.newsyt.repository.NewsRepository
import com.vikanshu.newsyt.utility.NewsUtility
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    val repository: NewsRepository
) : ViewModel() {

    private val DEFAULT_COUNTRY = "India"
    private val DEFAULT_LANGUAGE = "English"

    val saved = MutableLiveData<MutableList<com.vikanshu.newsyt.db.Article>>(mutableListOf())
    private val headlines: Map<String, MutableLiveData<MutableList<Article>>> = (
            mapOf(
                Constants.CATEGORIES.keys.toList()[0] to MutableLiveData(mutableListOf()),
                Constants.CATEGORIES.keys.toList()[1] to MutableLiveData(mutableListOf()),
                Constants.CATEGORIES.keys.toList()[2] to MutableLiveData(mutableListOf()),
                Constants.CATEGORIES.keys.toList()[3] to MutableLiveData(mutableListOf()),
                Constants.CATEGORIES.keys.toList()[4] to MutableLiveData(mutableListOf()),
                Constants.CATEGORIES.keys.toList()[5] to MutableLiveData(mutableListOf()),
                Constants.CATEGORIES.keys.toList()[6] to MutableLiveData(mutableListOf()),
                Constants.CATEGORIES.keys.toList()[7] to MutableLiveData(mutableListOf()),
                Constants.CATEGORIES.keys.toList()[8] to MutableLiveData(mutableListOf())
            )
            )
    private val searchData =
        MutableLiveData(
            Pair(
                Filters(
                    "",
                    Constants.CATEGORIES.keys.toList()[0],
                    1,
                    Constants.SORTBY.keys.toList()[0],
                    "1990/01/01",
                    NewsUtility.getDate(System.currentTimeMillis())
                ), mutableListOf<Article>()
            )
        )


    /**
     * headlines related methods
     */
    fun increasePageForCategory(category: String) {
        fetchHeadLines(category, (headlines[category]!!.value!!.size / 8) + 1, true)
    }

    fun refreshData(category: String) {
        fetchHeadLines(category, 1, true)
    }

    fun getHeadLines(category: String): MutableLiveData<MutableList<Article>> {
        if (headlines[category]!!.value!!.isEmpty()) {
            fetchHeadLines(category, 1, false)
        }
        return headlines[category]!!
    }

    private fun fetchHeadLines(category: String, page: Int, clearData: Boolean) {
        viewModelScope.launch {
            val response = repository.getArticles(
                category,
                DEFAULT_LANGUAGE,
                DEFAULT_COUNTRY,
                page
            )
            response?.let {
                headlines[category]!!.postValue(
                    mutableListOf<Article>().apply {
                        if (!clearData) addAll(headlines[category]!!.value!!)
                        addAll(it.articles)
                    }
                )
            }
        }
    }


    /**
     * search related methods
     */
    fun getSearchResults() = searchData

    fun querySearch(filters: Filters) {
        fetchSearchQuery(filters)
    }

    private fun fetchSearchQuery(filters: Filters) {
        viewModelScope.launch {
            filters.run {
                val response = repository.searchArticles(
                    query,
                    topic,
                    page,
                    DEFAULT_LANGUAGE,
                    DEFAULT_COUNTRY,
                    sortBy,
                    from,
                    to
                )
                searchData.postValue(
                    Pair(
                        filters,
                        response?.articles?.toMutableList() ?: mutableListOf()
                    )
                )
            }
        }
    }


    /**
     * db related methods
     */
    fun saveArticleInDB(article: com.vikanshu.newsyt.db.Article) {
        viewModelScope.launch {
            repository.saveArticleInDB(article)
        }
    }

    fun deleteArticleFromDB(article: com.vikanshu.newsyt.db.Article) {
        viewModelScope.launch {
            repository.removeArticleFromDB(article)
        }
    }

    fun getSavedArticles(): MutableLiveData<MutableList<com.vikanshu.newsyt.db.Article>> {
        if (saved.value!!.isEmpty()) {
            fetchAllSavedArticles()
        }
        return saved
    }

    private fun fetchAllSavedArticles() {
        viewModelScope.launch {
            saved.postValue(repository.getAllArticlesFromDB().toMutableList())
        }
    }

}