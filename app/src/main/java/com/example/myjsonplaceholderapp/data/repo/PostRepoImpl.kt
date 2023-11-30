package com.example.myjsonplaceholderapp.data.repo

import com.example.myjsonplaceholderapp.data.mapper.toPost
import com.example.myjsonplaceholderapp.data.remote.KtorApi
import com.example.myjsonplaceholderapp.domain.models.Post
import com.example.myjsonplaceholderapp.domain.repo.PostRepo

class PostRepoImpl constructor(
    private val ktorApi: KtorApi
): PostRepo {
    override suspend fun getPostsByUserId(userId: Int): List<Post> {
        return ktorApi.getPosts(userId)
            .map { it.toPost() }
    }
}