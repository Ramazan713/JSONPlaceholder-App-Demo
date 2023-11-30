package com.example.myjsonplaceholderapp.data.remote

import com.example.myjsonplaceholderapp.data.remote.dto.PostDtoResponse
import com.example.myjsonplaceholderapp.data.remote.dto.UserDtoResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class KtorApi constructor(
    private val client: HttpClient
) {

    suspend fun getUsers(): List<UserDtoResponse>{
        return client.get("https://jsonplaceholder.typicode.com/users") {
        }.body()
    }

    suspend fun getPosts(userId: Int): List<PostDtoResponse>{
        return client.get("https://jsonplaceholder.typicode.com/posts?userId=$userId")
            .body()
    }

}