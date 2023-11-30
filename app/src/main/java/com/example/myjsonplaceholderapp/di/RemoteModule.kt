package com.example.myjsonplaceholderapp.di

import android.util.Log
import com.example.myjsonplaceholderapp.data.remote.KtorApi
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.append
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val remoteModule = module {
    single {
        HttpClient(Android) {

            install(Logging){
                level = LogLevel.HEADERS
                logger = CustomAndroidHttpLogger
            }

            install(ContentNegotiation){
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
            install(DefaultRequest){
                //url("https://jsonplaceholder.typicode.com/")
                url {
                    protocol = URLProtocol.HTTPS
                    host = "jsonplaceholder.typicode.com"
                }
                headers {
                    append(HttpHeaders.ContentType,ContentType.Application.Json)
                }
            }

            install(HttpRequestRetry){
                maxRetries = 3
                exponentialDelay(maxDelayMs = 16000)
            }

//            install(HttpCache){
//
//            }

        }
    }

    single {
        KtorApi(get())
    }
}

private object CustomAndroidHttpLogger : Logger {
    private const val logTag = "asdasdsadsadasdda"

    override fun log(message: String) {
        Log.i(logTag, message)
    }
}