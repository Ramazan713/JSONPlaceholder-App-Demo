package com.example.myjsonplaceholderapp.domain.repo

import com.example.myjsonplaceholderapp.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepo {


    suspend fun refreshUsers()

    fun getFlowUsers(): Flow<List<User>>

    suspend fun deleteUser(user: User)

}