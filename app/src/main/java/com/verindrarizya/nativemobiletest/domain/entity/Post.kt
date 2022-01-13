package com.verindrarizya.nativemobiletest.domain.entity

data class Post(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int,
    var user: User? = null
) {
}
