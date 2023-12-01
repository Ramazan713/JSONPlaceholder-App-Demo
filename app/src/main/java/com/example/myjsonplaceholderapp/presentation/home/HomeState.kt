package com.example.myjsonplaceholderapp.presentation.home

import com.example.myjsonplaceholderapp.domain.models.User

data class HomeState(
    val isLoading: Boolean = false,
    val items: List<User> = emptyList(),
    val message: String? = null
)
