package com.example.myjsonplaceholderapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostDtoRequest(
    val body: String,
    val id: Int? = null,
    val title: String,
    val userId: Int
)