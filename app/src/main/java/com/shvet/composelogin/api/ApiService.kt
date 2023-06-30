package com.shvet.composelogin.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("")
    suspend fun login(
        @Field("userName") userName: String,
        @Field("userPassword") userPassword: String
    ): String
}