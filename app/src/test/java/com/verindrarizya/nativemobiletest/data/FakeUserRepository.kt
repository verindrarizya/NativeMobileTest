package com.verindrarizya.nativemobiletest.data

import com.verindrarizya.nativemobiletest.domain.entity.User
import com.verindrarizya.nativemobiletest.domain.repository.IUserRepository
import com.verindrarizya.nativemobiletest.testutil.DataDummy
import com.verindrarizya.nativemobiletest.util.DataMapper
import com.verindrarizya.nativemobiletest.util.Resource

class FakeUserRepository: IUserRepository {
    override suspend fun getUser(userId: Int): Resource<User> {
        val user = DataDummy.userResponses.first {
            it.id == userId
        }

        return Resource.Success(DataMapper.mapUserResponseToEntity(user))
    }
}