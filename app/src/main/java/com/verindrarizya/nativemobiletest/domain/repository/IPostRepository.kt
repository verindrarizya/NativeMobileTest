package com.verindrarizya.nativemobiletest.domain.repository

import com.verindrarizya.nativemobiletest.domain.entity.Comment
import com.verindrarizya.nativemobiletest.util.Resource
import com.verindrarizya.nativemobiletest.domain.entity.Post

interface IPostRepository {

    suspend fun getPosts(): Resource<List<Post>>

    suspend fun getComments(postId: Int): Resource<List<Comment>>
}