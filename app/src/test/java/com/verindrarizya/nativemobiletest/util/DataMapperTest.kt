package com.verindrarizya.nativemobiletest.util

import com.verindrarizya.nativemobiletest.testutil.DataDummy
import org.junit.Assert.*

import org.junit.Test

class DataMapperTest {

    @Test
    fun mapPostResponsesToEntities() {
        val expectedPostEntities = DataDummy.postEntities

        val postEntities = DataMapper.mapPostResponsesToEntities(DataDummy.postResponses)

        assertEquals(expectedPostEntities, postEntities)
    }

    @Test
    fun mapUserResponseToEntity() {
        val expectedUserEntity = DataDummy.userEntities[0]

        val userEntity = DataMapper.mapUserResponseToEntity(DataDummy.userResponses[0])

        assertEquals(expectedUserEntity, userEntity)
    }

    @Test
    fun mapCommentResponsestoEntities() {
        val expectedCommentEntities = DataDummy.commentEntities

        val commentEntities = DataMapper.mapCommentResponsestoEntities(DataDummy.commentResponses)

        assertEquals(expectedCommentEntities, commentEntities)
    }
}