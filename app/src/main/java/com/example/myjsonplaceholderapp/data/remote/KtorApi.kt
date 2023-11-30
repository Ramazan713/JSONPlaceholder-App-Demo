package com.example.myjsonplaceholderapp.data.remote

import com.example.myjsonplaceholderapp.data.remote.dto.PostDtoResponse
import com.example.myjsonplaceholderapp.data.remote.dto.UserDtoResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.path

class KtorApi constructor(
    private val client: HttpClient
) {

    suspend fun getUsers(): List<UserDtoResponse>{
        return client.get("users")
            .body()
    }

    suspend fun getPosts(userId: Int): List<PostDtoResponse>{
        return client.get {
            //"posts?userId=$userId"
            url {
                path("posts")
                parameter("userId",userId)
            }
        }
            .body()
    }

}