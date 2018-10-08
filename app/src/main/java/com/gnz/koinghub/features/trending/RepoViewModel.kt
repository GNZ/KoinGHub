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
import com.gnz.koinghub.service.TrendingReposRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable


class RepoViewModel(repository: TrendingReposRepository) : ViewModel() {

    private val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()

    val reposListLiveData by lazy {
        LivePagedListBuilder<Int, Repo>(repoDataSourceFactory, config).build()
    }

    private val composite = CompositeDisposable()

    private val repoDataSourceFactory = RepoDataSourceFactory(repository, composite, PAGE_SIZE)

    private val _repoClickLiveData by lazy {
        MutableLiveData<Repo>()
    }

    // Not exposing the mutable live data
    val repoClickLiveData: LiveData<Repo> = _repoClickLiveData

    fun startViewModel(clickObservable: Observable<Repo>) {
        composite.add(
            clickObservable
                    .subscribe(_repoClickLiveData::postValue)
        )
    }

    fun observeState(): LiveData<ResourceState> =
            Transformations.switchMap<RepoListDataSource, ResourceState>(
                    repoDataSourceFactory.repoListDataSourceLiveData
            ) { it.currentMoviesState }

    companion object {
        const val PAGE_SIZE = 30
    }
}