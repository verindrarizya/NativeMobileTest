package com.verindrarizya.nativemobiletest.data.remote.response

import com.google.gson.annotations.SerializedName

data class CommentResponse(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("postId")
	val postId: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("body")
	val body: String,

	@field:SerializedName("email")
	val email: String
)