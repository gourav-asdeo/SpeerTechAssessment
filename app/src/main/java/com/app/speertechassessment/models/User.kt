package com.app.speertechassessment.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User (
    @SerializedName("avatar_url")
    val avatar:String?="",
    @SerializedName("login")
    val username:String="",
    @SerializedName("name")
    val name:String?="",
    @SerializedName("bio")
    val description:String?="",
    @SerializedName("followers")
    val follower:Int=0,
    @SerializedName("following")
    val following:Int=0
): Serializable