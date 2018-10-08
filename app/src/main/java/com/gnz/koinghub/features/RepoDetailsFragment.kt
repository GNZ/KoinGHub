package com.gnz.koinghub.features


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.gnz.koinghub.R
import com.gnz.koinghub.data.Repo

class RepoDetailsFragment : Fragment() {

    companion object {

        fun newInstance(repo: Repo): RepoDetailsFragment {
            val bundle = Bundle().apply {

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
}
