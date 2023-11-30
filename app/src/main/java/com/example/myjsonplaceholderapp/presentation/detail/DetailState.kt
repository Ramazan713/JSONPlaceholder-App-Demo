package com.example.myjsonplaceholderapp.presentation.detail

import com.example.myjsonplaceholderapp.domain.models.Post

data class DetailState(
    val isLoading: Boolean = false,
    val items: List<Post> = emptyList()
)
