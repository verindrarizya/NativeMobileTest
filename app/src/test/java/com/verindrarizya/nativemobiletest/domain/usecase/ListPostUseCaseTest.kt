package com.verindrarizya.nativemobiletest.domain.usecase

import com.verindrarizya.nativemobiletest.data.FakePostRepository
import com.verindrarizya.nativemobiletest.data.FakeUserRepository
import com.verindrarizya.nativemobiletest.domain.entity.User
import com.verindrarizya.nativemobiletest.domain.repository.IPostRepository
import com.verindrarizya.nativemobiletest.domain.repository.IUserRepository
import com.verindrarizya.nativemobiletest.testutil.DataDummy
import com.verindrarizya.nativemobiletest.util.DataMapper
import com.verindrarizya.nativemobiletest.util.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ListPostUseCaseTest {

    private lateinit var postRepository: IPostRepository
    private lateinit var userRepository: IUserRepository
    private lateinit var listPostUseCase: ListPostUseCase

    @Before
    fun setUp() {
        postRepository = FakePostRepository()
        userRepository = FakeUserRepository()
        listPostUseCase = ListPostUseCase(userRepository, postRepository)
    }

    @Test
    fun invoke() = runBlocking {
        val posts = DataMapper.mapPostResponsesToEntities(DataDummy.postResponses)
        val usersResponses = DataDummy.userResponses
        val users = mutableListOf<User>()

        usersResponses.forEach {
            users.add(DataMapper.mapUserResponseToEntity(it))
        }
        posts.forEach { post ->
            post.user = users.find { user ->
                user.id == post.userId
            }
        }
        val expectedPosts = Resource.Success(posts)

        val result = listPostUseCase.invoke()

        assertEquals(expectedPosts, result)
    }

}