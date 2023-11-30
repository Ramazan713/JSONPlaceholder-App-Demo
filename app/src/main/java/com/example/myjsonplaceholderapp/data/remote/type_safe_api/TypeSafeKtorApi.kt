package com.example.myjsonplaceholderapp.data.remote.type_safe_api

import com.example.myjsonplaceholderapp.data.remote.KtorApi
import com.example.myjsonplaceholderapp.data.remote.dto.CommentDtoResponse
import com.example.myjsonplaceholderapp.data.remote.dto.PostDtoRequest
import com.example.myjsonplaceholderapp.data.remote.dto.PostDtoResponse
import com.example.myjsonplaceholderapp.data.remote.dto.UserDtoResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.*
import io.ktor.client.request.setBody

class TypeSafeKtorApi constructor(
    private val client: HttpClient
): KtorApi {

    override suspend fun getUsers(): List<UserDtoResponse> {
        return client.get(UsersResource()).body()
    }

    override suspend fun deleteUserById(userId: Int) {
        client.delete(UsersResource.Id(id = userId))
    }

    override suspend fun getCommentsByPostId(postId: Int): List<CommentDtoResponse> {
        return client.get(CommentsResource(postId = postId)).body()
    }

    override suspend fun deleteCommentById(commentId: Int) {
        client.delete(CommentsResource.Id(id = commentId))
    }

    override suspend fun getPosts(userId: Int): List<PostDtoResponse> {
        return client.get(PostsResource(userId)).body()
    }

    override suspend fun deletePostById(postId: Int) {
        client.delete(PostsResource.Id(id = postId))
    }

    override suspend fun addPost(requestBody: PostDtoRequest): PostDtoResponse {
        return client.post(PostsResource()){
            setBody(requestBody)
        }.body()
    }

    override suspend fun updatePost(requestBody: PostDtoRequest): PostDtoResponse {
        return client.put(PostsResource.Id(id = requestBody.id!!)){
            setBody(requestBody)
        }.body()
    }
}