package com.verindrarizya.nativemobiletest.data.remote.response

import com.google.gson.annotations.SerializedName

data class GeoResponse(

	@field:SerializedName("lng")
	val lng: String,

	@field:SerializedName("lat")
	val lat: String
)