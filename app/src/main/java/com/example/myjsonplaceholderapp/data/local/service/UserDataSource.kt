package com.example.myjsonplaceholderapp.data.local.service

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.Posts
import com.example.UserQueries
import com.example.Users
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class UserDataSource constructor(
    private val userQueries: UserQueries,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
)  {

    fun getUsers(): Flow<List<Users>>{
        return userQueries.getUsers()
            .asFlow()
            .mapToList(ioDispatcher)
    }

    suspend fun getUserById(userId: Int): Users?{
        return withContext(ioDispatcher){
            userQueries
                .getUserById(userId.toLong())
                .executeAsOneOrNull()
        }
    }

    suspend fun deleteUserById(id: Int){
        withContext(ioDispatcher){
            userQueries.deleteUserById(id.toLong())
        }
    }

    suspend fun insertUser(user: Users){
        withContext(ioDispatcher){
            userQueries.insertUser(user)
        }
    }

    suspend fun insertUsers(users: List<Users>){
        withContext(ioDispatcher){
            userQueries.transaction {
                users.forEach { user->
                    userQueries.insertUser(user)
                }
            }
        }
    }
}