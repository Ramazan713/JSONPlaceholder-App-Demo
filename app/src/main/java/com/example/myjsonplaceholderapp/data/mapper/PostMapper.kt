package com.example.myjsonplaceholderapp.data.mapper

import com.example.myjsonplaceholderapp.data.remote.dto.PostDtoResponse
import com.example.myjsonplaceholderapp.domain.models.Post

fun PostDtoResponse.toPost(): Post{
    return Post(
        body, id, title, userId
    )
}