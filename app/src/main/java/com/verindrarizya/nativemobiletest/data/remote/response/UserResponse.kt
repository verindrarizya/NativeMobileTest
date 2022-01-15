package com.verindrarizya.nativemobiletest.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @field:SerializedName("website")
	val website: String,

    @field:SerializedName("address")
	val address: AddressResponse,

    @field:SerializedName("phone")
	val phone: String,

    @field:SerializedName("name")
	val name: String,

    @field:SerializedName("company")
	val company: CompanyResponse,

    @field:SerializedName("id")
	val id: Int,

    @field:SerializedName("email")
	val email: String,

    @field:SerializedName("username")
	val username: String
)