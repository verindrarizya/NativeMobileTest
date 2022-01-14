package com.verindrarizya.nativemobiletest.domain.usecase

import com.verindrarizya.nativemobiletest.domain.entity.Comment
import com.verindrarizya.nativemobiletest.domain.repository.IPostRepository
import com.verindrarizya.nativemobiletest.util.Resource
import javax.inject.Inject

class PostCommentUseCase @Inject constructor(private val postRepository: IPostRepository) {

    suspend fun invoke(postId: Int): Resource<List<Comment>> {
        return postRepository.getComments(postId)
    }

}