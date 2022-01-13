package com.verindrarizya.nativemobiletest.data

import com.verindrarizya.nativemobiletest.data.remote.api.service.UserService
import com.verindrarizya.nativemobiletest.util.DataMapper
import com.verindrarizya.nativemobiletest.util.Resource
import com.verindrarizya.nativemobiletest.domain.entity.User
import com.verindrarizya.nativemobiletest.domain.repository.IUserRepository
import java.lang.Exception
import javax.inject.Inject

class UserRepository @Inject constructor(private val userService: UserService): IUserRepository {
    override suspend fun getUser(userId: Int): Resource<User> {
        try {
            val response = userService.getUser(userId)
            val mappedEntity = DataMapper.mapUserResponseToEntity(response)
            return Resource.Success(mappedEntity)
        } catch (e: Exception) {
            return Resource.Error(e.message ?: "something went wrong")
        }
    }
}