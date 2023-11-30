package com.example.myjsonplaceholderapp.data.mapper

import com.example.Posts
import com.example.myjsonplaceholderapp.data.remote.dto.PostDtoResponse
import com.example.myjsonplaceholderapp.domain.models.Post

fun PostDtoResponse.toPost(): Post{
    return Post(
        body, id, title, userId
    )
}

fun PostDtoResponse.toPostEntity(): Posts {
    return Posts(
        id.toLong(), userId.toLong(), body, title,
    )
}

fun Posts.toPost(): Post {
    return Post(
        id = id.toInt(),
        userId = userId.toInt(),
        body = body,
        title = title
    )
}