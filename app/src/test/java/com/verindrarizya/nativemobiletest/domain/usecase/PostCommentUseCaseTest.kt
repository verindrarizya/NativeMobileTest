package com.verindrarizya.nativemobiletest.domain.usecase

import com.verindrarizya.nativemobiletest.data.FakePostRepository
import com.verindrarizya.nativemobiletest.domain.repository.IPostRepository
import com.verindrarizya.nativemobiletest.testutil.DataDummy
import com.verindrarizya.nativemobiletest.util.DataMapper
import com.verindrarizya.nativemobiletest.util.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class PostCommentUseCaseTest {

    private lateinit var postRepository: IPostRepository
    private lateinit var postCommentUseCase: PostCommentUseCase

    @Before
    fun setUp() {
        postRepository = FakePostRepository()
        postCommentUseCase = PostCommentUseCase(postRepository)
    }

    @Test
    fun invoke() = runBlocking {
        val postId = 1
        val expectedComment = DataMapper.mapCommentResponsestoEntities(DataDummy.commentResponses.filter {
            it.postId == postId
        })
        val expectedValue = Resource.Success(expectedComment)

        val result = postCommentUseCase.invoke(postId)

        assertEquals(expectedValue, result)
    }

    @Test
    fun invokeExpectResourceError() = runBlocking {
        val postId = 999
        val expectedValue = Resource.Error("")

        val result = postCommentUseCase.invoke(postId)

        assertEquals(expectedValue, result)
    }
}