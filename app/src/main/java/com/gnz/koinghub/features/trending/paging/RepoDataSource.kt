package com.gnz.koinghub.features.trending.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.gnz.koinghub.data.*
import com.gnz.koinghub.service.GithubApi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber


open class RepoDataSourceFactory(
        val api: GithubApi,
        private val composite: CompositeDisposable) : DataSource.Factory<Int, Repo>() {

    val movieListDataSourceLiveData = MutableLiveData<RepoListDataSource>()

    override fun create(): DataSource<Int, Repo> {
        val repoListDataSource = RepoListDataSource(api, composite)
        movieListDataSourceLiveData.postValue(repoListDataSource)
        return repoListDataSource
    }
}

class RepoListDataSource(
        private val api: GithubApi,
        private val composite: CompositeDisposable) : PageKeyedDataSource<Int, Repo>() {

    val currentMoviesState = MutableLiveData<ResourceState>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Repo>) {
        getTrendingRepos(INITIAL_PAGE) { repoList ->
            callback.onResult(repoList, null, INITIAL_PAGE + 1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Repo>) {
        getTrendingRepos(params.key) { repoList ->
            callback.onResult(repoList, params.key + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Repo>) {
        val before = if (params.key == INITIAL_PAGE) {
            null
        } else {
            params.key - 1
        }
        getTrendingRepos(params.key) { repoList ->
            callback.onResult(repoList, before)
        }
    }

    private fun getTrendingRepos(page: Int, callback: (List<Repo>) -> Unit) {
        currentMoviesState.postValue(Loading)
        composite.add(
                api.trendingAndroidRepos(PAGE_SIZE, page).subscribeBy(
                        onSuccess = { repoList ->
                            when {
                                repoList.items.isEmpty() -> currentMoviesState.postValue(EmptyState)
                                else -> {
                                    currentMoviesState.postValue(PopulateState)
                                    callback.invoke(repoList.items)
                                }
                            }
                        },
                        onError = { throwable ->
                            Timber.e(throwable, "Error loading the repositories")
                            currentMoviesState.postValue(ErrorState(throwable))
                        }
                )
        )
    }

    companion object {
        private const val INITIAL_PAGE = 1
        const val PAGE_SIZE = 30
    }
}