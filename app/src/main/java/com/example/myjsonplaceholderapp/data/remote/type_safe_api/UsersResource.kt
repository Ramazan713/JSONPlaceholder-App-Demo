package com.example.myjsonplaceholderapp.data.remote.type_safe_api

import io.ktor.resources.Resource

@Resource("/users")
class UsersResource {

    @Resource("{id}")
    class Id(val parent: UsersResource = UsersResource(),val id: Int)
}