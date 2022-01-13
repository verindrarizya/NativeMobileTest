package com.verindrarizya.nativemobiletest.util

import com.verindrarizya.nativemobiletest.data.remote.response.PostResponse
import com.verindrarizya.nativemobiletest.data.remote.response.UserResponse
import com.verindrarizya.nativemobiletest.domain.entity.Post
import com.verindrarizya.nativemobiletest.domain.entity.User

object DataMapper {

    fun mapPostResponseToEntities(postsResponse: List<PostResponse>): List<Post> {
        val postEntities = mutableListOf<Post>()

        postsResponse.forEach { postResponse: PostResponse ->
            postEntities.add(Post(
                id = postResponse.id,
                userId = postResponse.userId,
                title = postResponse.title,
                body = postResponse.body
            ))
        }

        return postEntities
    }

    fun mapUserResponseToEntity(userResponse: UserResponse): User {
        return User(
            id = userResponse.id,
            username = userResponse.username,
            companyName = userResponse.company.name
        )
    }
}