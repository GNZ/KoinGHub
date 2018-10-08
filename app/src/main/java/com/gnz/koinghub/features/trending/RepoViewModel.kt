package com.gnz.koinghub.features.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gnz.koinghub.data.Repo
import com.gnz.koinghub.data.ResourceState
import com.gnz.koinghub.features.trending.paging.RepoDataSourceFactory
import com.gnz.koinghub.features.trending.paging.RepoListDataSource
import com.gnz.koinghub.service.ReposRepository
import io.reactivex.disposables.CompositeDisposable


class RepoViewModel(repository: ReposRepository) : ViewModel() {

    private val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()

    val reposListLiveData by lazy {
        LivePagedListBuilder<Int, Repo>(repoDataSourceFactory, config).build()
    }

    private val composite = CompositeDisposable()

    private val repoDataSourceFactory = RepoDataSourceFactory(repository, composite, PAGE_SIZE)

    fun observeState(): LiveData<ResourceState> =
            Transformations.switchMap<RepoListDataSource, ResourceState>(
                    repoDataSourceFactory.movieListDataSourceLiveData
            ) { it.currentMoviesState }

    companion object {
        const val PAGE_SIZE = 30
    }
}