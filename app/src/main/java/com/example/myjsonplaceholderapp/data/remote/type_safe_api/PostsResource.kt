package com.example.myjsonplaceholderapp.data.remote.type_safe_api

import io.ktor.resources.Resource


@Resource("/posts")
class PostsResource(val userId: Int? = null) {

    @Resource("{id}")
    class Id(val parent: PostsResource = PostsResource(), val id: Int)
}