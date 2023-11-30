package com.example.myjsonplaceholderapp.data.local.service

import com.example.PostQueries
import com.example.Posts
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostDataSource constructor(
    private val postQueries: PostQueries,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getPostsByUserId(userId: Int): List<Posts>{
        return withContext(ioDispatcher){
            postQueries
                .getPostByUserId(userId.toLong())
                .executeAsList()
        }
    }

    suspend fun deletePostById(id: Int){
        withContext(ioDispatcher){
            postQueries.deletePostById(id.toLong())
        }
    }

    suspend fun insertPost(post: Posts){
        withContext(ioDispatcher){
            postQueries.insertPost(post)
        }
    }

    suspend fun insertPosts(posts: List<Posts>){
        withContext(ioDispatcher){
            postQueries.transaction {
                posts.forEach { post->
                    postQueries.insertPost(post)
                }
            }
        }
    }


}