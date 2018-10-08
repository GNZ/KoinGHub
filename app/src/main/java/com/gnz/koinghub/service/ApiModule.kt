package com.gnz.koinghub.service

import org.koin.dsl.module.module
import retrofit2.Retrofit


val apiModule = module {
    single { createApi(get()) }
    single { createRemoteRepo(get()) }
}

fun createApi(retrofit: Retrofit): GithubApi = retrofit.create(GithubApi::class.java)

fun createRemoteRepo(api: GithubApi) = RemoteReposRepository(api)