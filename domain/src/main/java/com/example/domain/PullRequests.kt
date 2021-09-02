package com.example.domain

interface PullRequests {
    fun getPullRequestsForRepo(organisationName: String, repoName: String, pullRequestType: Boolean, receiver: PullRequestDataReceiver)
}