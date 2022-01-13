package com.verindrarizya.nativemobiletest.data.remote.response

import com.google.gson.annotations.SerializedName

data class Address(

	@field:SerializedName("zipcode")
	val zipcode: String,

	@field:SerializedName("geo")
	val geo: Geo,

	@field:SerializedName("suite")
	val suite: String,

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("street")
	val street: String
)