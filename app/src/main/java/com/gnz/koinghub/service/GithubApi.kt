package com.gnz.koinghub.service

import com.gnz.koinghub.data.RepoList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface GithubApi {

    @GET("search/repositories?sort=stars")
    fun searchRepositories(@Query("q") query: String,
                           @Query("per_page") perPage: Int,
                           @Query("page") page: Int):
            Single<RepoList>
}