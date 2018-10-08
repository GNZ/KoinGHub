package com.gnz.koinghub.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gnz.koinghub.R
import com.gnz.koinghub.data.Repo
import com.gnz.koinghub.features.details.RepoDetailsFragment
import com.gnz.koinghub.features.trending.RepoListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .add(R.id.containerLayout, RepoListFragment.newInstance(), RepoListFragment.TAG)
                .commit()
    }

    fun openDetails(repo: Repo) {
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .add(R.id.containerLayout, RepoDetailsFragment.newInstance(repo), RepoDetailsFragment.TAG)
                .addToBackStack(null)
                .commit()
    }
}