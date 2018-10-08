package com.gnz.koinghub.service

import com.gnz.koinghub.data.Repo
import io.reactivex.Single


class RemoteReposRepository(private val api: GithubApi) : ReposRepository {

    override fun getTrendingRepos(date: String, pageSize: Int, page: Int): Single<List<Repo>> =
            api.trendingAndroidRepos(date, pageSize, page)
                    .map { it.items }
}