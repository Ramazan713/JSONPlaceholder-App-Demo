package com.example.myjsonplaceholderapp.domain.utils

import kotlinx.coroutines.CancellationException

class ExceptionHandler {

    suspend fun<T> coHandle(
        onError: (e: Exception) -> T,
        onTryFunc: suspend () -> T,
    ): T {
        return Companion.coHandle(onError,onTryFunc)
    }

    fun<T> handle(
        onError: (e: Exception) -> T,
        onTryFunc: () -> T,
    ): T {
        return Companion.handle(onError,onTryFunc)
    }

    companion object {
        suspend fun<T> coHandle(
            onError: (e: Exception) -> T,
            onTryFunc: suspend () -> T,
        ): T{
            return try {
                onTryFunc()
            }catch (e: Exception){
                if(e is CancellationException) throw e
                onError(e)
            }
        }

        fun<T> handle(
            onError: (e: Exception) -> T,
            onTryFunc: () -> T,
        ): T{
            return try {
                onTryFunc()
            }catch (e: Exception){
                if(e is CancellationException) throw e
                onError(e)
            }
        }
    }
}