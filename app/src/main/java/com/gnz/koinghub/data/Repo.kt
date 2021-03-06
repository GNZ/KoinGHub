package com.gnz.koinghub.data

data class Repo(
        val id: Int,
        val node_id: String,
        val name: String,
        val full_name: String,
        val owner: Owner,
        val private: Boolean,
        val html_url: String,
        val description: String,
        val fork: Boolean,
        val url: String,
        val created_at: String,
        val updated_at: String,
        val pushed_at: String,
        val homepage: String,
        val size: Int,
        val stargazers_count: Int,
        val watchers_count: Int,
        val language: String,
        val forks_count: Int,
        val open_issues_count: Int,
        val master_branch: String,
        val default_branch: String,
        val score: Double
)