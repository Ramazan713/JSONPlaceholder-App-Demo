package com.example.myjsonplaceholderapp.data.repo

import android.util.Log
import com.example.myjsonplaceholderapp.data.local.service.UserDataSource
import com.example.myjsonplaceholderapp.data.mapper.toUser
import com.example.myjsonplaceholderapp.data.mapper.toUserEntity
import com.example.myjsonplaceholderapp.data.remote.KtorApi
import com.example.myjsonplaceholderapp.domain.models.User
import com.example.myjsonplaceholderapp.domain.repo.UserRepo

class UserRepoImpl constructor(
    private val api: KtorApi,
    private val userDataSource: UserDataSource
): UserRepo {
    override suspend fun getUsers(refresh: Boolean): List<User> {
        var userEntities = userDataSource.getUsers()

        if(userEntities.isEmpty() || refresh){
           try {
               val userEntitiesFromDto = api.getUsers().map { it.toUserEntity() }
               userDataSource.insertUsers(userEntitiesFromDto)
               Log.d("asdasdsadsadasdda","userSucceed:")
           }catch (e: Exception){
               Log.d("asdasdsadsadasdda","errorUser: ${e.localizedMessage}")
           }

            userEntities = userDataSource.getUsers()
        }
        return userEntities.map { it.toUser() }
    }
}