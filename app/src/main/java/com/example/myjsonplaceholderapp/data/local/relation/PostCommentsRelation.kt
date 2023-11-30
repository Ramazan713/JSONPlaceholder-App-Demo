package com.example.myjsonplaceholderapp.data.local.relation

import com.example.Comments
import com.example.DetailPostView

data class PostCommentsRelation(
    val post: DetailPostView,
    val comments: List<Comments>
)
