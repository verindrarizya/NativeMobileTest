package com.verindrarizya.nativemobiletest.data.remote.api.service

import com.verindrarizya.nativemobiletest.data.remote.response.PostResponse
import retrofit2.http.GET

interface PostService {

    /**
     * endpoint list of post
     */
    @GET("posts")
    suspend fun getPosts(): List<PostResponse>

}