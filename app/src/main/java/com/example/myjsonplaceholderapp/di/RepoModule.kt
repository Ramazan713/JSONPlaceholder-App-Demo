package com.example.myjsonplaceholderapp.di

import com.example.myjsonplaceholderapp.data.repo.PostRepoImpl
import com.example.myjsonplaceholderapp.data.repo.UserRepoImpl
import com.example.myjsonplaceholderapp.domain.repo.PostRepo
import com.example.myjsonplaceholderapp.domain.repo.UserRepo
import org.koin.dsl.module

val repoModule = module {

    single<UserRepo> {
        UserRepoImpl(get(),get(),get())
    }

    single<PostRepo> {
        PostRepoImpl(get(),get(),get())
    }
}