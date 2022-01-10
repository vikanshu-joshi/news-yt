package com.vikanshu.newsyt.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Article::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

}