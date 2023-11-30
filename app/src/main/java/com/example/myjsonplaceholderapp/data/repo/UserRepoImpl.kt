package com.example.myjsonplaceholderapp.data.repo

import android.util.Log
import com.example.myjsonplaceholderapp.data.local.service.UserDataSource
import com.example.myjsonplaceholderapp.data.mapper.toUser
import com.example.myjsonplaceholderapp.data.mapper.toUserEntity
import com.example.myjsonplaceholderapp.data.remote.KtorApi
import com.example.myjsonplaceholderapp.data.remote.KtorApiImpl
import com.example.myjsonplaceholderapp.domain.models.User
import com.example.myjsonplaceholderapp.domain.repo.UserRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepoImpl constructor(
    private val api: KtorApi,
    private val userDataSource: UserDataSource
): UserRepo {
    override suspend fun refreshUsers() {
        try {
            val userEntitiesFromDto = api.getUsers().map { it.toUserEntity() }
            userDataSource.insertUsers(userEntitiesFromDto)
        }catch (e: Exception){
            Log.d("asdasdsadsadasdda","errorUser: ${e.localizedMessage}")
        }
    }

    override fun getFlowUsers(): Flow<List<User>> {
        return userDataSource.getUsers()
            .map { users->
                users.map {
                    it.toUser()
                }
            }
    }

    override suspend fun deleteUser(user: User) {
        try {
            api.deleteUserById(user.id)
            userDataSource.deleteUserById(user.id)
        }catch (e: Exception){
            Log.d("asdasdsadsadasdda","errorUser: ${e.localizedMessage}")
        }
    }
}