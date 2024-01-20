package com.wahyuhw.mealrecipes.base

import com.google.gson.annotations.SerializedName

data class BaseErrorResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("details")
    val details: String?,
    @SerializedName("hint")
    val hint: String?,
    @SerializedName("message")
    val message: String?
)