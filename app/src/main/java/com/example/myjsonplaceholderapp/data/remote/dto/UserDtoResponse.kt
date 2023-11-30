package com.example.myjsonplaceholderapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDtoResponse(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)