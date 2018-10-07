package com.gnz.koinghub.service

import retrofit2.http.GET
import retrofit2.http.Query


interface GithubApi {

    @GET("search/repositories?q=android+language:java+language:kotlin&sort=stars")
    fun trendingAndroidRepos(@Query("per_page") perPage: Int, @Query("page") page: Int)
}