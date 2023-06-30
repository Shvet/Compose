package com.shvet.composelogin.login.repository

import com.shvet.composelogin.api.ApiService
import javax.inject.Inject

class LoginRepository @Inject constructor(private val service: ApiService) {
    suspend fun login(userName: String, userPassword: String): String {
        return service.login(userName = userName, userPassword = userPassword)
    }
}