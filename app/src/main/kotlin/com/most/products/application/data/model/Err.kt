package com.most.products.application.data.model

import com.google.gson.annotations.SerializedName

data class Err(
    @SerializedName("message")
    var message: String = "",
)