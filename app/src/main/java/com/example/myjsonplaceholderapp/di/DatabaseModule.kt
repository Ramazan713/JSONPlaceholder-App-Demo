package com.example.myjsonplaceholderapp.di

import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.AppDatabase
import com.example.myjsonplaceholderapp.data.TransactionProviderImpl
import com.example.myjsonplaceholderapp.data.local.service.PostDataSource
import com.example.myjsonplaceholderapp.data.local.service.UserDataSource
import com.example.myjsonplaceholderapp.domain.TransactionProvider
import org.koin.dsl.module


val databaseModule = module {

    single<SqlDriver> {
        AndroidSqliteDriver(
            AppDatabase.Schema,
            get(),
            "appDb.db",
            callback = object : AndroidSqliteDriver.Callback(AppDatabase.Schema){
                override fun onOpen(db: SupportSQLiteDatabase) {
                    db.setForeignKeyConstraintsEnabled(true)
                }
            }
        )
    }

    single {
        AppDatabase(get())
    }

    single {
        PostDataSource(get())
    }

    single {
        UserDataSource(get<AppDatabase>().userQueries)
    }

    factory<TransactionProvider> {
        TransactionProviderImpl(get())
    }
}