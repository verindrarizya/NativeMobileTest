package com.verindrarizya.nativemobiletest.domain.repository

import com.verindrarizya.nativemobiletest.util.Resource
import com.verindrarizya.nativemobiletest.domain.entity.User

interface IUserRepository {

    suspend fun getUser(userId: Int): Resource<User>

}