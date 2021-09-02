package com.example.domain

import com.example.entity.PullRequest
import com.example.repository.APIConstants
import com.example.repository.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PullRequestsImpl @Inject constructor(private val service: GithubService): PullRequests {
    override fun getPullRequestsForRepo(organisationName: String, repoName: String, pullRequestType: Boolean, receiver: PullRequestDataReceiver) {
        service.getPullRequests(organisationName, repoName, if (pullRequestType) APIConstants.STATE_OPEN else APIConstants.STATE_CLOSED).enqueue(object: Callback<List<PullRequest>> {
            override fun onResponse(call: Call<List<PullRequest>>, response: Response<List<PullRequest>>) {
                if(response.isSuccessful) {
                    response.body()?.let { receiver.onSuccess(it) }
                } else {
                    receiver.onFailure(response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                receiver.onFailure(t.message)
            }
        })
    }
}