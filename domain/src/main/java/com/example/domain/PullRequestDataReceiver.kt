package com.example.domain

import com.example.entity.PullRequest

abstract class PullRequestDataReceiver {
    abstract fun onSuccess(pullRequests: List<PullRequest>)
    abstract fun onFailure(message: String?)
}