package com.verindrarizya.nativemobiletest.data

import com.verindrarizya.nativemobiletest.data.remote.api.service.PostService
import com.verindrarizya.nativemobiletest.domain.repository.IPostRepository
import com.verindrarizya.nativemobiletest.testutil.DataDummy
import com.verindrarizya.nativemobiletest.util.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostRepositoryTest {

    private lateinit var postService: PostService
    private lateinit var postRepository: IPostRepository

    private val postId = 1

    @Before
    fun setUp() {
        postService = mock(PostService::class.java)
        postRepository = PostRepository(postService)
    }

    @Test
    fun getPosts() = runBlocking {
        val expectedValue = Resource.Success(DataDummy.postEntities)
        `when`(postService.getPosts()).thenReturn(DataDummy.postResponses)

        val result = postRepository.getPosts()

        verify(postService).getPosts()
        assertEquals(expectedValue, result)
    }

    @Test
    fun getPostsExpectResourceError() = runBlocking {
        val expectedValue = Resource.Error("")
        `when`(postService.getPosts()).thenThrow(RuntimeException(""))

        val result = postRepository.getPosts()

        verify(postService).getPosts()
        assertEquals(expectedValue, result)
    }

    @Test
    fun getComments() = runBlocking {
        val expectedValue = Resource.Success(DataDummy.commentEntities)
        `when`(postService.getComments(postId)).thenReturn(DataDummy.commentResponses)

        val result = postRepository.getComments(postId)

        verify(postService).getComments(postId)
        assertEquals(expectedValue, result)
    }

    @Test
    fun getCommentsExpectResourceError() = runBlocking {
        val expectedValue = Resource.Error("")
        `when`(postService.getComments(postId)).thenThrow(RuntimeException(""))

        val result = postRepository.getComments(postId)

        verify(postService).getComments(postId)
        assertEquals(expectedValue, result)
    }
}