package com.verindrarizya.nativemobiletest.data.remote.api.service

import com.verindrarizya.nativemobiletest.data.remote.response.CommentResponse
import com.verindrarizya.nativemobiletest.data.remote.response.PostResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {

    /**
     * endpoint list of post
     */
    @GET("posts")
    suspend fun getPosts(): List<PostResponse>

    /**
     * endpoint of post's comments
     */
    @GET("posts/{postId}/comments")
    suspend fun getComments(@Path("postId") postId: Int): List<CommentResponse>

}