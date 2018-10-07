package com.gnz.koinghub.data

data class RepoList(
        val total_count: Int,
        val incomplete_results: Boolean,
        val items: List<Repo>
)