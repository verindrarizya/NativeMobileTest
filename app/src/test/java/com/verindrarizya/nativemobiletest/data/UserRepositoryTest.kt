package com.verindrarizya.nativemobiletest.data

import com.verindrarizya.nativemobiletest.data.remote.api.service.UserService
import com.verindrarizya.nativemobiletest.domain.repository.IUserRepository
import com.verindrarizya.nativemobiletest.testutil.DataDummy
import com.verindrarizya.nativemobiletest.util.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserRepositoryTest {

    private lateinit var userService: UserService
    private lateinit var userRepository: IUserRepository

    private val userId = 1

    @Before
    fun setUp() {
        userService = mock(UserService::class.java)
        userRepository = UserRepository(userService)
    }

    @Test
    fun getUser() =  runBlocking {
        val expectedValue = Resource.Success(DataDummy.userEntities[0])

        `when`(userService.getUser(userId)).thenReturn(DataDummy.userResponses[0])

        val result = userRepository.getUser(userId)

        assertEquals(expectedValue, result)
    }

    @Test
    fun getUserExpectResource() =  runBlocking {
        val expectedValue = Resource.Error("")

        `when`(userService.getUser(userId)).thenThrow(RuntimeException(""))

        val result = userRepository.getUser(userId)

        assertEquals(expectedValue, result)
    }
}