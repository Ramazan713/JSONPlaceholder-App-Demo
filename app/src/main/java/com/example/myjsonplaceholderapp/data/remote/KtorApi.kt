package com.example.myjsonplaceholderapp.data.remote

import com.example.myjsonplaceholderapp.data.remote.dto.CommentDtoResponse
import com.example.myjsonplaceholderapp.data.remote.dto.PostDtoRequest
import com.example.myjsonplaceholderapp.data.remote.dto.PostDtoResponse
import com.example.myjsonplaceholderapp.data.remote.dto.UserDtoResponse

interface KtorApi {

    suspend fun getUsers(): List<UserDtoResponse>

    suspend fun deleteUserById(userId: Int)

    suspend fun getCommentsByPostId(postId: Int): List<CommentDtoResponse>

    suspend fun deleteCommentById(commentId: Int)

    suspend fun getPosts(userId: Int): List<PostDtoResponse>

    suspend fun deletePostById(postId: Int)

    suspend fun addPost(requestBody: PostDtoRequest): PostDtoResponse

    suspend fun updatePost(requestBody: PostDtoRequest): PostDtoResponse
}