package com.example.myjsonplaceholderapp.di

import com.example.myjsonplaceholderapp.presentation.detail.DetailViewModel
import com.example.myjsonplaceholderapp.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        HomeViewModel(get())
    }

    viewModel {
        DetailViewModel(get(),get())
    }
}