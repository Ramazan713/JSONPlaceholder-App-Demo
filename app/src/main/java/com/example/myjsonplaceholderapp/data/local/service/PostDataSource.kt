package com.example.myjsonplaceholderapp.data.local.service

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.PostQueries
import com.example.Posts
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class PostDataSource constructor(
    private val postQueries: PostQueries,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

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