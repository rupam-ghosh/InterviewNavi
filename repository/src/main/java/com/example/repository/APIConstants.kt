package com.example.repository

class APIConstants {
    companion object {
        const val GITHUB_API_BASE_URL = "https://api.github.com";
        const val PATH_ORGANISATION = "organisation"
        const val PATH_REPO = "repo"
        const val PULL_REQUESTS_API = "/repos/{${PATH_ORGANISATION}}/{${PATH_REPO}}/pulls";
        const val STATE_CLOSED = "closed"
        const val STATE_OPEN = "open"
        const val QUERY_STATE = "state"
    }
}