package com.example.myjsonplaceholderapp.data.repo

import android.util.Log
import com.example.myjsonplaceholderapp.data.local.service.DetailPostDataSource
import com.example.myjsonplaceholderapp.data.local.service.PostDataSource
import com.example.myjsonplaceholderapp.data.mapper.toPost
import com.example.myjsonplaceholderapp.data.mapper.toPostDtoRequest
import com.example.myjsonplaceholderapp.data.mapper.toPostEntity
import com.example.myjsonplaceholderapp.data.remote.KtorApi
import com.example.myjsonplaceholderapp.data.remote.dto.PostDtoRequest
import com.example.myjsonplaceholderapp.domain.models.Post
import com.example.myjsonplaceholderapp.domain.repo.PostRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PostRepoImpl constructor(
    private val ktorApi: KtorApi,
    private val postDataSource: PostDataSource,
    private val detailPostDataSource: DetailPostDataSource
): PostRepo {
    override suspend fun refreshPosts(userId: Int) {
        try {
            val postEntitiesFromDto = ktorApi.getPosts(userId).map { it.toPostEntity() }
            postDataSource.insertPosts(postEntitiesFromDto)
        }catch (e: Exception){
            Log.d("asdasdsadsadasdda","postError: ${e.localizedMessage}")
        }
    }

    override suspend fun getFlowPostsByUserId(userId: Int): Flow<List<Post>> {
        return detailPostDataSource.getPostsByUserId(userId)
            .map { items->
                items.map { it.toPost() }
            }
    }

    override suspend fun updatePost(updatedPost: Post) {
        try {
            val responseDto = ktorApi.updatePost(updatedPost.toPostDtoRequest())
            postDataSource.insertPost(responseDto.toPostEntity())
        }catch (e: Exception){
            Log.d("asdasdsadsadasdda","postError: ${e.localizedMessage}")
        }
    }

    override suspend fun addPost(content: String, userId: Int) {
        try {
            val requestDto = PostDtoRequest(
                body = content,
                userId = userId,
                title = "$userId title"
            )
            val responseDto = ktorApi.addPost(requestDto)
            postDataSource.insertPost(responseDto.toPostEntity())
        }catch (e: Exception){
            Log.d("asdasdsadsadasdda","postError: ${e.localizedMessage}")
        }
    }

    override suspend fun deletePostById(postId: Int) {
        try {
            ktorApi.deletePostById(postId)
            postDataSource.deletePostById(postId)
        }catch (e: Exception){
            Log.d("asdasdsadsadasdda","deletePostByIdError: ${e.localizedMessage}")
        }
    }
}