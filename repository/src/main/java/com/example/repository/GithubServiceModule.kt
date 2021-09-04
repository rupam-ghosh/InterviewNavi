package com.example.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ViewModelComponent::class)
object GithubServiceModule {

    @ViewModelScoped
    @Provides
    fun provideAnalyticsService(): GithubService {
        val okHttpClient = OkHttpClient.Builder().
        connectTimeout(10, TimeUnit.SECONDS).
        callTimeout(10, TimeUnit.SECONDS).
        writeTimeout(10, TimeUnit.SECONDS).
        readTimeout(10, TimeUnit.SECONDS).build()
        return Retrofit.Builder()
            .baseUrl(APIConstants.GITHUB_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(GithubService::class.java)
    }
}