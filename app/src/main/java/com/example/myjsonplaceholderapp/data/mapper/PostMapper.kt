package com.example.myjsonplaceholderapp.data.mapper

import com.example.DetailPostView
import com.example.Posts
import com.example.myjsonplaceholderapp.data.remote.dto.PostDtoRequest
import com.example.myjsonplaceholderapp.data.remote.dto.PostDtoResponse
import com.example.myjsonplaceholderapp.domain.models.Post

fun Post.toPostDtoRequest(): PostDtoRequest{
    return PostDtoRequest(
        body, id, title, userId
    )
}

fun PostDtoResponse.toPostEntity(): Posts {
    return Posts(
        id.toLong(), userId.toLong(), body, title,
    )
}

fun DetailPostView.toPost(): Post {
    return Post(
        id = id.toInt(),
        userId = userId.toInt(),
        body = body,
        title = title,
        username = username
    )
}