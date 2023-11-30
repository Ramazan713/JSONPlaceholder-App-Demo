package com.example.myjsonplaceholderapp.domain.models

data class PostComments(
    val post: Post,
    val comments: List<Comment>
)
