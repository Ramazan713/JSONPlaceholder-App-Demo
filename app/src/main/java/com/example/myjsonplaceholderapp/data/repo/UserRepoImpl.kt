package com.example.myjsonplaceholderapp.data.repo

import android.util.Log
import com.example.Comments
import com.example.myjsonplaceholderapp.data.local.service.UserDataSource
import com.example.myjsonplaceholderapp.data.mapper.toEntity
import com.example.myjsonplaceholderapp.data.mapper.toPostEntity
import com.example.myjsonplaceholderapp.data.mapper.toUser
import com.example.myjsonplaceholderapp.data.mapper.toUserEntity
import com.example.myjsonplaceholderapp.data.remote.KtorApi
import com.example.myjsonplaceholderapp.data.remote.KtorApiImpl
import com.example.myjsonplaceholderapp.domain.models.User
import com.example.myjsonplaceholderapp.domain.repo.UserRepo
import com.example.myjsonplaceholderapp.domain.utils.ExceptionHandler
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class UserRepoImpl constructor(
    private val api: KtorApi,
    private val userDataSource: UserDataSource,
    private val exceptionHandler: ExceptionHandler
): UserRepo {
    override suspend fun refreshUsers(): Result<Unit> {
        return exceptionHandler.coHandle(
            onError = { Result.failure(it) }
        ){
            val userEntitiesFromDto = api.getUsers().map { it.toUserEntity() }
            withContext(NonCancellable){
                userDataSource.insertUsers(userEntitiesFromDto)
            }
            Result.success(Unit)
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

    override suspend fun deleteUser(user: User): Result<Unit> {
        return exceptionHandler.coHandle(
            onError = { Result.failure(it) }
        ){
            api.deleteUserById(user.id)
            withContext(NonCancellable){
                userDataSource.deleteUserById(user.id)
            }
            Result.success(Unit)
        }
    }
}