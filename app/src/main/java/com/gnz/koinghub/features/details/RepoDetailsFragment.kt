package com.gnz.koinghub.features.details


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.gnz.koinghub.R
import com.gnz.koinghub.data.Repo
import kotlinx.android.synthetic.main.fragment_repo_details.*
import android.content.Intent
import android.net.Uri
import android.widget.TextView


class RepoDetailsFragment : Fragment() {

    companion object {
        private val NAME_EXTRA = "NAME_EXTRA"
        private val STARS_EXTRA = "STARS_EXTRA"
        private val FORK_EXTRA = "FORK_EXTRA"
        private val WATCH_EXTRA = "WATCH_EXTRA"
        private val DESCRIPTION_EXTRA = "DESCRIPTION_EXTRA"
        private val URL_EXTRA = "URL_EXTRA"

        val TAG = RepoDetailsFragment::class.simpleName

        fun newInstance(repo: Repo): RepoDetailsFragment {
            val bundle = Bundle().apply {
                putString(NAME_EXTRA, repo.full_name)
                putInt(STARS_EXTRA, repo.stargazers_count)
                putInt(FORK_EXTRA, repo.forks_count)
                putInt(WATCH_EXTRA, repo.watchers_count)
                putString(DESCRIPTION_EXTRA, repo.description)
                putString(URL_EXTRA, repo.html_url)
            }
            return RepoDetailsFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repo_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {

            if (it.containsKey(NAME_EXTRA)) {
                repoNameTextView.text = it.getString(NAME_EXTRA)
            }

            if (it.containsKey(STARS_EXTRA)) {
                starsTextView.text = it.getInt(STARS_EXTRA).toString()
            }

            if (it.containsKey(FORK_EXTRA)) {
                forksTextView.text = it.getInt(FORK_EXTRA).toString()
            }

            if (it.containsKey(WATCH_EXTRA)) {
                watchTextView.text = it.getInt(WATCH_EXTRA).toString()
            }

            if (it.containsKey(DESCRIPTION_EXTRA)) {
                descriptionTextView.text = it.getString(DESCRIPTION_EXTRA)
            }

            if (it.containsKey(URL_EXTRA)) {
                urlTextView.text = it.getString(URL_EXTRA)
                urlTextView.setOnClickListener { view ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse((view as TextView).text.toString()))
                    startActivity(intent)
                }
            }
        }
    }
}
