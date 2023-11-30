package com.example.myjsonplaceholderapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Geo(
    val lat: String,
    val lng: String
)