package com.wahyuhw.mealrecipes.base

import com.google.gson.annotations.SerializedName

data class BaseResponse <T>(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("meals")
    val data: T?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?
)