package com.vikanshu.newsyt.di

import android.content.Context
import androidx.room.Room
import com.vikanshu.newsyt.Constants.DATABASE_NAME
import com.vikanshu.newsyt.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideArticlesDatabase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun providesArticlesDao(
        appDatabase: AppDatabase
    ) = appDatabase.getArticleDao()
}