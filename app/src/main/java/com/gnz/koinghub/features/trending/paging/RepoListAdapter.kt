package com.gnz.koinghub.features.trending.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gnz.koinghub.R
import com.gnz.koinghub.application.extensions.language
import com.gnz.koinghub.data.Repo
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.repo_viewholder.view.*


class RepoListAdapter : PagedListAdapter<Repo, RepoViewHolder>(ItemCallback) {

    private val clickSubject = PublishSubject.create<Repo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder =
            RepoViewHolder(parent)

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        getItem(position)?.let { repo ->
            holder.repoName.text = repo.full_name
            holder.repoDescription.text = repo.description
            with(holder.itemView.context) {
                val language = repo.language.language(holder.itemView.context)
                holder.repoDetails.text = getString(R.string.repo_details)
                        .format(repo.language, repo.stargazers_count)
                holder.itemView.setOnClickListener {
                    clickSubject.onNext(repo)
                }
            }
        }
    }

    fun observeRepoClick(): Observable<Repo> = clickSubject

    private object ItemCallback : DiffUtil.ItemCallback<Repo>() {

        override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem == newItem

        override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem.id != newItem.id
    }
}

class RepoViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.repo_viewholder, parent, false)) {
    val repoName = itemView.repoNameTextView
    val repoDescription = itemView.descriptionTextView
    val repoDetails = itemView.detailsTextView
}