package com.example.myjsonplaceholderapp.data.mapper

import com.example.Users
import com.example.myjsonplaceholderapp.data.remote.dto.UserDtoResponse
import com.example.myjsonplaceholderapp.domain.models.User

fun UserDtoResponse.toUser(): User{
    return User(id, name, username, email)
}

fun UserDtoResponse.toUserEntity(): Users{
    return Users(id.toLong(), name, username, email)
}

fun Users.toUser(): User{
    return User(id.toInt(), name, username, email)
}