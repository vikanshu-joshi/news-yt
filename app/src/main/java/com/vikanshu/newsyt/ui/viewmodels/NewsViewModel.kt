package com.vikanshu.newsyt.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.vikanshu.newsyt.db.ArticleDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    val articleDao: ArticleDao
)
    : ViewModel() {
}