package com.verindrarizya.nativemobiletest.domain.usecase

import com.verindrarizya.nativemobiletest.util.Resource
import com.verindrarizya.nativemobiletest.domain.entity.Post
import com.verindrarizya.nativemobiletest.domain.repository.IPostRepository
import com.verindrarizya.nativemobiletest.domain.repository.IUserRepository
import javax.inject.Inject

class ListPostUseCase @Inject constructor(
    private val userRepository: IUserRepository,
    private val postRepository: IPostRepository
) {

    suspend fun invoke(): Resource<List<Post>> {
        val posts = postRepository.getPosts()

        when (posts) {
            is Resource.Success -> {
                posts.data.forEach { post: Post ->
                    val user = userRepository.getUser(post.userId)

                    when (user) {
                        is Resource.Success -> post.user = user.data
                        is Resource.Error -> return Resource.Error(user.errorMessage)
                    }
                }
            }
            is Resource.Error -> {
                return Resource.Error(posts.errorMessage)
            }
        }

        return posts
    }

}