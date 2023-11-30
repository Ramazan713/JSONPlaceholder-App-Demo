package com.example.myjsonplaceholderapp.domain.repo

import com.example.myjsonplaceholderapp.domain.models.Post
import kotlinx.coroutines.flow.Flow

interface PostRepo {

    suspend fun refreshPosts(userId: Int)

    suspend fun getFlowPostsByUserId(userId: Int): Flow<List<Post>>

    suspend fun updatePost(updatedPost: Post)

    suspend fun addPost(content: String, userId: Int)

    suspend fun deletePostById(postId: Int)
}