package com.gnz.koinghub.service

import com.gnz.koinghub.data.Repo
import io.reactivex.Single


class TrendingRemoteReposRepository(private val api: GithubApi) : TrendingReposRepository {

    override fun getTrendingAndroidRepos(date: String, pageSize: Int, page: Int): Single<List<Repo>> {
        val query = "android+language:java+language:kotlin+created:>$date"
        return api.searchRepositories(query, pageSize, page)
                .map { it.items }
    }
}