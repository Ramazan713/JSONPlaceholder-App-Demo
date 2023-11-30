package com.example.myjsonplaceholderapp.data.local.service

import com.example.Posts
import com.example.UserQueries
import com.example.Users
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDataSource constructor(
    private val userQueries: UserQueries,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
)  {

    suspend fun getUsers(): List<Users>{
        return userQueries.getUsers()
            .executeAsList()
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