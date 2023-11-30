package com.example.myjsonplaceholderapp.presentation.utils

import com.example.myjsonplaceholderapp.domain.models.Post
import com.example.myjsonplaceholderapp.domain.models.User

object SampleData{

    val user = User(id = 1, name = "User", username = "username.x", email = "example@gmail.com")

    val post = Post("post body",1,"title",1)

}