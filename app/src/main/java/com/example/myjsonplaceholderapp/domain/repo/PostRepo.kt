package com.example.myjsonplaceholderapp.domain.repo

import com.example.myjsonplaceholderapp.domain.models.Post

interface PostRepo {

    suspend fun getPostsByUserId(userId: Int, refresh: Boolean = false): List<Post>
}