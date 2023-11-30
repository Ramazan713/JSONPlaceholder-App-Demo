package com.example.myjsonplaceholderapp.di

import com.example.myjsonplaceholderapp.data.repo.PostRepoImpl
import com.example.myjsonplaceholderapp.data.repo.UserRepoImpl
import com.example.myjsonplaceholderapp.domain.repo.PostRepo
import com.example.myjsonplaceholderapp.domain.repo.UserRepo
import com.example.myjsonplaceholderapp.presentation.detail.DetailViewModel
import com.example.myjsonplaceholderapp.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single<UserRepo> {
        UserRepoImpl(get(),get())
    }

    single<PostRepo> {
        PostRepoImpl(get(),get())
    }

    viewModel {
        HomeViewModel(get())
    }

    viewModel {
        DetailViewModel(get(),get())
    }
}

