package com.gnz.koinghub.service

import com.gnz.koinghub.data.Repo
import io.reactivex.Single


interface TrendingReposRepository {

    fun getTrendingAndroidRepos(date: String, pageSize: Int, page: Int): Single<List<Repo>>
}