package com.example.myjsonplaceholderapp.data.mapper

import com.example.myjsonplaceholderapp.data.remote.dto.UserDtoResponse
import com.example.myjsonplaceholderapp.domain.models.User

fun UserDtoResponse.toUser(): User{
    return User(id, name, username, email)
}