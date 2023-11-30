package com.example.myjsonplaceholderapp.data

import com.example.AppDatabase
import com.example.myjsonplaceholderapp.domain.TransactionProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TransactionProviderImpl constructor(
    private val db: AppDatabase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): TransactionProvider {
    override suspend fun execute(body: () -> Unit) {
        withContext(ioDispatcher){
            db.transaction {
                body()
            }
        }
    }
}