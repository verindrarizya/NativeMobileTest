package com.verindrarizya.nativemobiletest.data

import com.verindrarizya.nativemobiletest.data.remote.api.service.PostService
import com.verindrarizya.nativemobiletest.domain.entity.Comment
import com.verindrarizya.nativemobiletest.util.DataMapper
import com.verindrarizya.nativemobiletest.util.Resource
import com.verindrarizya.nativemobiletest.domain.entity.Post
import com.verindrarizya.nativemobiletest.domain.repository.IPostRepository
import javax.inject.Inject

class PostRepository @Inject constructor (private val postService: PostService): IPostRepository {
    override suspend fun getPosts(): Resource<List<Post>> {
        try {
            val response = postService.getPosts()
            val mappedEntities = DataMapper.mapPostResponsesToEntities(response)
            return Resource.Success(mappedEntities)
        } catch (e: Exception) {
            return Resource.Error(e.message ?: "something went wrong")
        }
    }

    override suspend fun getComments(postId: Int): Resource<List<Comment>> {
        try {
            val response = postService.getComments(postId)
            val mappedEntities = DataMapper.mapCommentResponsestoEntities(response)
            return Resource.Success(mappedEntities)
        } catch (e: Exception) {
            return Resource.Error(e.message ?: "something went wrong")
        }
    }
}