package com.example.myjsonplaceholderapp.data.remote

import com.example.myjsonplaceholderapp.data.remote.dto.PostDtoRequest
import com.example.myjsonplaceholderapp.data.remote.dto.PostDtoResponse
import com.example.myjsonplaceholderapp.data.remote.dto.UserDtoResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.path

class KtorApiImpl constructor(
    private val client: HttpClient
): KtorApi {

    override suspend fun getUsers(): List<UserDtoResponse>{
        return client.get("users")
            .body()
    }

    override suspend fun deleteUserById(userId: Int) {
        client.delete {
            url {
                path("users",userId.toString())
            }
        }
    }

    override suspend fun getPosts(userId: Int): List<PostDtoResponse>{
        return client.get {
            url {
                path("posts")
                parameter("userId",userId)
            }
        }.body()
    }

    override suspend fun deletePostById(postId: Int){
        client.delete {
            url {
                path("posts")
                parameter("id",postId)
            }
        }
    }


    override suspend fun addPost(requestBody: PostDtoRequest): PostDtoResponse{
        return client.post {
            url {
                path("posts")
            }
            setBody(requestBody)
        }.body()
    }

    override suspend fun updatePost(requestBody: PostDtoRequest): PostDtoResponse{
        return client.put {
            url {
                path("posts",requestBody.id.toString())
            }
            setBody(requestBody)
        }.body()
    }

}