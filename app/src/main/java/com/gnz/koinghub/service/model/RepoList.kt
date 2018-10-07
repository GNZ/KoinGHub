package com.gnz.koinghub.service.model

data class RepoList(
        val total_count: Int,
        val incomplete_results: Boolean,
        val items: List<Repo>
)