package com.example.myjsonplaceholderapp.data.repo

import android.util.Log
import com.example.Comments
import com.example.myjsonplaceholderapp.data.local.service.PostDataSource
import com.example.myjsonplaceholderapp.data.mapper.toEntity
import com.example.myjsonplaceholderapp.data.mapper.toPostComments
import com.example.myjsonplaceholderapp.data.mapper.toPostDtoRequest
import com.example.myjsonplaceholderapp.data.mapper.toPostEntity
import com.example.myjsonplaceholderapp.data.remote.KtorApi
import com.example.myjsonplaceholderapp.data.remote.dto.PostDtoRequest
import com.example.myjsonplaceholderapp.domain.models.Post
import com.example.myjsonplaceholderapp.domain.models.PostComments
import com.example.myjsonplaceholderapp.domain.repo.PostRepo
import com.example.myjsonplaceholderapp.domain.utils.ExceptionHandler
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PostRepoImpl constructor(
    private val ktorApi: KtorApi,
    private val postDataSource: PostDataSource,
    private val exceptionHandler: ExceptionHandler
): PostRepo {

    override suspend fun refreshPosts(userId: Int): Result<Unit>{
        return exceptionHandler.coHandle(
            onError = { Result.failure(it) }
        ){
            val comments = mutableListOf<Comments>()
            val postEntitiesFromDto = ktorApi.getPosts(userId).map { it.toPostEntity() }
            postEntitiesFromDto.forEach { post->
                ktorApi.getCommentsByPostId(post.id.toInt()).map { it.toEntity() }.apply {
                    comments.addAll(this)
                }
            }
            withContext(NonCancellable){
                postDataSource.insertData(comments,postEntitiesFromDto)
            }
            Result.success(Unit)
        }
    }

    override suspend fun getFlowPostsByUserId(userId: Int): Flow<List<PostComments>> {
        return postDataSource.getPostsByUserId(userId)
            .map { items->
                items.map { it.toPostComments() }
            }
    }

    override suspend fun updatePost(updatedPost: Post): Result<Unit> {
        return exceptionHandler.coHandle(
            onError = { Result.failure(it) }
        ){
            val responseDto = ktorApi.updatePost(updatedPost.toPostDtoRequest())
            withContext(NonCancellable){
                postDataSource.updatePost(responseDto.toPostEntity())
            }
            Result.success(Unit)
        }
    }

    override suspend fun addPost(content: String, userId: Int): Result<Unit> {
        return exceptionHandler.coHandle(
            onError = { Result.failure(it) }
        ){
            val requestDto = PostDtoRequest(
                body = content,
                userId = userId,
                title = "$userId title"
            )
            val responseDto = ktorApi.addPost(requestDto)
            withContext(NonCancellable){
                postDataSource.insertPost(responseDto.toPostEntity())
            }
            Result.success(Unit)
        }
    }

    override suspend fun deletePostById(postId: Int): Result<Unit> {
        return exceptionHandler.coHandle(
            onError = { Result.failure(it) }
        ){
            ktorApi.deletePostById(postId)
            withContext(NonCancellable){
                postDataSource.deletePostById(postId)
            }
            Result.success(Unit)
        }
    }

    override suspend fun deleteCommentById(commentId: Int): Result<Unit> {
        return exceptionHandler.coHandle(
            onError = { Result.failure(it) }
        ){
            ktorApi.deleteCommentById(commentId)
            withContext(NonCancellable){
                postDataSource.deleteCommentById(commentId)
            }
            Result.success(Unit)
        }
    }
}