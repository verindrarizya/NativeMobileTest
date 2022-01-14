package com.verindrarizya.nativemobiletest.util

import com.verindrarizya.nativemobiletest.data.remote.response.CommentResponse
import com.verindrarizya.nativemobiletest.data.remote.response.PostResponse
import com.verindrarizya.nativemobiletest.data.remote.response.UserResponse
import com.verindrarizya.nativemobiletest.domain.entity.Comment
import com.verindrarizya.nativemobiletest.domain.entity.Post
import com.verindrarizya.nativemobiletest.domain.entity.User

object DataMapper {

    fun mapPostResponsesToEntities(postResponses: List<PostResponse>): List<Post> {
        val postEntities = mutableListOf<Post>()

        postResponses.forEach { postResponse: PostResponse ->
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

    fun mapCommentResponsestoEntities(commentResponses: List<CommentResponse>): List<Comment> {
        val commentEntities = mutableListOf<Comment>()

        commentResponses.forEach { commentResponse: CommentResponse ->
            commentEntities.add(Comment(
                name = commentResponse.name,
                id = commentResponse.id,
                body = commentResponse.body,
                email = commentResponse.email
            ))
        }

        return commentEntities
    }
}