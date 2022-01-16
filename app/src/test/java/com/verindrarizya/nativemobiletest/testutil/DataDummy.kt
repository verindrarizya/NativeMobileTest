package com.verindrarizya.nativemobiletest.testutil

import com.verindrarizya.nativemobiletest.data.remote.response.*
import com.verindrarizya.nativemobiletest.domain.entity.Comment
import com.verindrarizya.nativemobiletest.domain.entity.Post
import com.verindrarizya.nativemobiletest.domain.entity.User

object DataDummy {

    private val titleTemplate = "faketitle"
    private val bodyTemplate = "fakebody"
    private val emailTemplate = "fakeemail"
    private val nameTemplate = "fakename"

    private val zipcodeTemplate = "fakezipcode"
    private val suiteTemplate = "fakesuite"
    private val cityTemplate = "fakecity"
    private val streetTemplate = "fakecity"

    private val lngTemplate = "fakelng"
    private val latTemplate = "fakelat"

    private val bsTemplate = "fakebs"
    private val catchPhraseTemplate = "fakecatchphrase"
    private val companyNameTemplate = "fakecompanyname"

    private val websiteTemplate = "fakewebsite"
    private val phoneTemplate = "fakephone"
    private val usernameTemplate = "fakeusername"

    val postResponses: List<PostResponse>
        get() {
            val value = mutableListOf<PostResponse>()

            for (i in 1..3) {
                value.add(PostResponse(
                    i,
                    "$titleTemplate$i",
                        "$bodyTemplate$i",
                        i
                ))
            }

            return value
        }

    val postEntities: List<Post>
        get() {
            val value = mutableListOf<Post>()

            for (postResponse in postResponses) {
                value.add(Post(
                    postResponse.id,
                    postResponse.title,
                    postResponse.body,
                    postResponse.userId
                ))
            }

            return value
        }

    val commentResponses: List<CommentResponse>
        get() {
            val value = mutableListOf<CommentResponse>()

            for (i in 1..3) {
                value.add(CommentResponse(
                    "$nameTemplate$i",
                    i,
                    i,
                    "$bodyTemplate$i",
                    "$emailTemplate$i"
                ))
            }

            return value
        }

    val commentEntities: List<Comment>
        get() {
            val value = mutableListOf<Comment>()

            for (comment in commentResponses) {
                value.add(Comment(
                    comment.name,
                    comment.id,
                    comment.body,
                    comment.email
                ))
            }

            return value
        }

    private val geoResponses: List<GeoResponse>
        get() {
            val value = mutableListOf<GeoResponse>()

            for(i in 1..3) {
                value.add(GeoResponse(
                    "$lngTemplate$i",
                    "$latTemplate$i"
                ))
            }

            return value
        }

    private val addressResponses: List<AddressResponse>
        get() {
            val value = mutableListOf<AddressResponse>()

            for (i in 0..2) {
                value.add(
                    AddressResponse(
                        "$zipcodeTemplate${i+1}",
                        geoResponses[i],
                        "$suiteTemplate${i+1}",
                        "$cityTemplate${i+1}",
                        "$streetTemplate${i+1}"
                ))
            }

            return value
        }

    private val companyResponses: List<CompanyResponse>
        get() {
            val value = mutableListOf<CompanyResponse>()

            for(i in 1..3) {
                value.add(CompanyResponse(
                    "$bsTemplate$i",
                    "$catchPhraseTemplate$i",
                    "$companyNameTemplate$i"
                ))
            }

            return value
        }

    val userResponses: List<UserResponse>
        get() {
            val value = mutableListOf<UserResponse>()

            for(i in 0..2) {
                value.add(UserResponse(
                    "$websiteTemplate${i+1}",
                    addressResponses[i],
                    "$phoneTemplate${i+1}",
                    "$nameTemplate${i+1}",
                    companyResponses[i],
                    i+1,
                    "$emailTemplate${i+1}",
                    "$usernameTemplate${i+1}"
                ))
            }

            return value
        }

    val userEntities: List<User>
        get() {
            val value = mutableListOf<User>()

            for(userResponse in userResponses) {
                value.add(User(
                    userResponse.id,
                    userResponse.username,
                    userResponse.company.name
                ))
            }

            return value
        }
}