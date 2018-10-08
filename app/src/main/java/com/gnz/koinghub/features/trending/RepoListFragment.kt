package com.gnz.koinghub.features.trending


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.paging.PagedList
import com.gnz.koinghub.R
import com.gnz.koinghub.application.extensions.observe
import com.gnz.koinghub.application.extensions.visibleOrGone
import com.gnz.koinghub.data.*
import com.gnz.koinghub.features.trending.paging.RepoListAdapter
import kotlinx.android.synthetic.main.fragment_repo_list.*
import org.jetbrains.anko.support.v4.toast
import org.koin.androidx.viewmodel.ext.android.viewModel


class RepoListFragment : Fragment() {

    companion object {
        val TAG = RepoListFragment::class.simpleName
        fun newInstance() = RepoListFragment()
    }

    val reposViewModel by viewModel<RepoViewModel>()

    private lateinit var repoAdapter: RepoListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initData()
    }

    private fun initViews() {
        repoAdapter = RepoListAdapter()
        reposRecyclerView.adapter = repoAdapter
    }

    private fun initData() {
        with(reposViewModel) {
            observe(observeState(), ::showState)
            observe(reposListLiveData, ::showResult)
        }
    }

    private fun showState(resourceState: ResourceState) = when (resourceState) {
        is Loading -> showLoadingState(true)
        is PopulateState -> showLoadingState(false)
        is EmptyState -> showLoadingState(false)
        is ErrorState -> showErrorState()
    }

    private fun showLoadingState(showLoading: Boolean) {
        progressBar.visibleOrGone(showLoading)
    }

    private fun showErrorState() {
        showLoadingState(false)
        toast(R.string.error_loading_repos)
    }

    private fun showResult(pagedList: PagedList<Repo>) =
            repoAdapter.submitList(pagedList)
}
