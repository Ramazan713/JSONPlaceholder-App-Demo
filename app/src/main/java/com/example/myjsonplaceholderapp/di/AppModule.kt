package com.example.myjsonplaceholderapp.di

import com.example.myjsonplaceholderapp.domain.utils.ExceptionHandler
import org.koin.dsl.module


val appModule = module {


    factory {
        ExceptionHandler()
    }


}

