package com.verindrarizya.nativemobiletest.data

import com.verindrarizya.nativemobiletest.domain.entity.Comment
import com.verindrarizya.nativemobiletest.domain.entity.Post
import com.verindrarizya.nativemobiletest.domain.entity.User
import com.verindrarizya.nativemobiletest.domain.repository.IPostRepository
import com.verindrarizya.nativemobiletest.testutil.DataDummy
import com.verindrarizya.nativemobiletest.util.DataMapper
import com.verindrarizya.nativemobiletest.util.Resource

class FakePostRepository: IPostRepository {

    private val errorMessage = ""
    private val userRepository = FakeUserRepository()

    override suspend fun getPosts(): Resource<List<Post>> {
        val posts = DataMapper.mapPostResponsesToEntities(DataDummy.postResponses)

        posts.forEach { post ->
            val user = (userRepository.getUser(post.userId) as Resource.Success).data
            post.user = user
        }

        return Resource.Success(posts)
    }

    override suspend fun getComments(postId: Int): Resource<List<Comment>> {
        try {
            val result = DataDummy.commentResponses.filter {
                it.postId == postId
            }

            if(result.isNotEmpty()) {
                return Resource.Success(DataMapper.mapCommentResponsestoEntities(result))
            } else {
                /**
                 * Case to trigger Resource.Error return value
                 */
                throw Exception(errorMessage)
            }
        } catch (e: Exception) {
            return Resource.Error(errorMessage)
        }
    }
}