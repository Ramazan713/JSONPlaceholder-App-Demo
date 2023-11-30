package com.example.myjsonplaceholderapp.data.local.service

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.DetailPostView
import com.example.PostViewQueries
import com.example.Posts
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class DetailPostDataSource constructor(
    private val postViewQueries: PostViewQueries,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    fun getPostsByUserId(userId: Int): Flow<List<DetailPostView>> {
        return postViewQueries
            .getDetailPostsByUserId(userId.toLong())
            .asFlow()
            .mapToList(ioDispatcher)
    }

}