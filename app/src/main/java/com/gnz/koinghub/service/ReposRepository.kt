package com.gnz.koinghub.service

import com.gnz.koinghub.data.Repo
import io.reactivex.Single


interface ReposRepository {

    fun getTrendingRepos(date: String, pageSize: Int, page: Int): Single<List<Repo>>
}