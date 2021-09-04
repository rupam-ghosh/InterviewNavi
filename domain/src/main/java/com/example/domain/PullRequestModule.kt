package com.example.domain

import com.example.domain.PullRequests
import com.example.domain.PullRequestsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class PullRequestModule {
    @ViewModelScoped
    @Binds
    abstract fun bindPullRequests(pullRequestsImpl: PullRequestsImpl): PullRequests
}