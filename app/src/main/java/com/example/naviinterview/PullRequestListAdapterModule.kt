package com.example.naviinterview

import com.example.naviinterview.PullRequestListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.FragmentScoped

@InstallIn(ViewModelComponent::class)
@Module
class PullRequestListAdapterModule {
    @FragmentScoped
    @Provides
    fun providePullRequestListAdapter(): PullRequestListAdapter {
        return PullRequestListAdapter()
    }
}