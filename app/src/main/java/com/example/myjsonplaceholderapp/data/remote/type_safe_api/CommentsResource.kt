package com.example.myjsonplaceholderapp.data.remote.type_safe_api

import io.ktor.resources.Resource

@Resource("/comments")
class CommentsResource(val postId: Int? = null) {

    @Resource("{id}")
    class Id(val parent: CommentsResource = CommentsResource(), val id: Int)
}