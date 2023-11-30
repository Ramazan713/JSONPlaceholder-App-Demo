package com.example.myjsonplaceholderapp.domain.repo

import com.example.myjsonplaceholderapp.domain.models.User

interface UserRepo {

    suspend fun getUsers(refresh: Boolean = false): List<User>

}