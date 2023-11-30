package com.example.myjsonplaceholderapp.data.mapper

import com.example.myjsonplaceholderapp.data.local.relation.PostCommentsRelation
import com.example.myjsonplaceholderapp.domain.models.PostComments

fun PostCommentsRelation.toPostComments(): PostComments{
    return PostComments(
        post = post.toPost(),
        comments = comments.map { it.toComment() }
    )
}