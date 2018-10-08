package com.gnz.koinghub.features.trending

import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val repoListModule = module {

    viewModel { RepoViewModel(get()) }
}