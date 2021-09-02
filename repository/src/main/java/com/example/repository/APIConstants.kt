package com.example.repository

class APIConstants {
    companion object {
        // const val PERSONAL_ACCESS_TOKEN = "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";
        const val GITHUB_API_BASE_URL = "https://api.github.com";
        const val PATH_ORGANISATION = "organisation"
        const val PATH_REPO = "repo"
        const val PULL_REQUESTS_API = "/repos/{${PATH_ORGANISATION}}/{${PATH_REPO}}/pulls";
        const val SAMPLE_ORGANISATION_NAME = "vmg";
        const val SAMPLE_REPO_NAME = "redcarpet";
        const val STATE_CLOSED = "closed"
        const val STATE_OPEN = "open"
        const val QUERY_STATE = "state"
    }
}