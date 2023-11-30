package com.example.myjsonplaceholderapp.data.repo

import com.example.myjsonplaceholderapp.data.mapper.toUser
import com.example.myjsonplaceholderapp.data.remote.KtorApi
import com.example.myjsonplaceholderapp.domain.models.User
import com.example.myjsonplaceholderapp.domain.repo.UserRepo

class UserRepoImpl constructor(
    private val api: KtorApi
): UserRepo {
    override suspend fun getUsers(): List<User> {
        return api.getUsers().map { it.toUser() }
    }
}