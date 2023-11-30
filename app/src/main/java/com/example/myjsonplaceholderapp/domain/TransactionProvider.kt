package com.example.myjsonplaceholderapp.domain

interface TransactionProvider {

    suspend fun execute(body: () -> Unit)
}