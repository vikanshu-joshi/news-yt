package com.vikanshu.newsyt.di

import android.content.Context
import androidx.room.Room
import com.vikanshu.newsyt.BuildConfig
import com.vikanshu.newsyt.Constants.DATABASE_NAME
import com.vikanshu.newsyt.api.NewsApiRequest
import com.vikanshu.newsyt.db.AppDatabase
import com.vikanshu.newsyt.db.ArticleDao
import com.vikanshu.newsyt.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
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


    @Singleton
    @Provides
    fun providesHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val request =
                chain.request().newBuilder().addHeader("X-Api-Key", BuildConfig.API_KEY).build()
            Timber.d("%s -> %s", request.method(), request.url().toString())
            chain.proceed(request)
        }
        return httpClient.build()
    }


    @Singleton
    @Provides
    fun providesRetrofit(httpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApiRequest::class.java)


    @Singleton
    @Provides
    fun providesNewsRepository(
        articleDao: ArticleDao,
        API: NewsApiRequest
    ) = NewsRepository(articleDao, API)
}