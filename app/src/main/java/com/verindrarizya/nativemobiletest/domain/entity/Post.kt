package com.verindrarizya.nativemobiletest.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int,
    var user: User? = null
): Parcelable
