package com.example.repository

import com.example.entity.PullRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @GET(APIConstants.PULL_REQUESTS_API)
    fun getPullRequests(@Path(APIConstants.PATH_ORGANISATION) organisationName: String,@Path(APIConstants.PATH_REPO)  repoName: String, @Query(APIConstants.QUERY_STATE) state: String): Call<List<PullRequest>>;
}