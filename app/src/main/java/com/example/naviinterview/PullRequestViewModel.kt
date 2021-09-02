package com.example.Naviinterview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.PullRequestDataReceiver
import com.example.domain.PullRequests
import com.example.entity.PullRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PullRequestViewModel @Inject constructor(private val pullRequests: PullRequests) :
    ViewModel() {
    val pullRequestsLiveData: MutableLiveData<List<PullRequest>> = MutableLiveData()
    val progressBarLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val messageLiveData: MutableLiveData<String> = MutableLiveData()

    fun getPullRequests(organisation: String, repoName: String, pullRequestType: Boolean) {
        progressBarLiveData.postValue(true)
        messageLiveData.postValue("Loading...")
        pullRequestsLiveData.postValue(listOf())
        val receiver = object : PullRequestDataReceiver() {
            override fun onSuccess(pullRequests: List<PullRequest>) {
                pullRequestsLiveData.postValue(pullRequests)
                progressBarLiveData.postValue(false)
                messageLiveData.postValue(if (pullRequests.isNotEmpty()) "" else "No pull requests to show")
            }

            override fun onFailure(message: String?) {
                progressBarLiveData.postValue(false)
                messageLiveData.postValue(if (message != null && message.isNotEmpty()) message else "Could not fetch pull requests")
            }
        }

        pullRequests.getPullRequestsForRepo(
                organisation,
                repoName,
                pullRequestType,
                receiver)
    }
}