package com.gnz.koinghub.features.trending

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gnz.koinghub.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .add(R.id.containerLayout, RepoListFragment.newInstance(), "REPO_LIST")
                .commit()
    }
}
