package com.example.myjsonplaceholderapp.presentation.detail

import com.example.myjsonplaceholderapp.domain.models.Post
import com.example.myjsonplaceholderapp.domain.models.PostComments

data class DetailState(
    val isLoading: Boolean = false,
    val items: List<PostComments> = emptyList(),
    val dialogEvent: DetailDialogEvent? = null
)
