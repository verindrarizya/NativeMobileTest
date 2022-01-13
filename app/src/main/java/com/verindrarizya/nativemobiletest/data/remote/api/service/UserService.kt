package com.verindrarizya.nativemobiletest.data.remote.api.service

import com.verindrarizya.nativemobiletest.data.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    /**
     * endpoint of a user
     */

    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId: Int): UserResponse

}