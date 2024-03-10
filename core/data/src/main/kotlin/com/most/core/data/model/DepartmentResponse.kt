package com.most.core.data.model

import com.google.gson.annotations.SerializedName

data class DepartmentResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("desc")
    val desc: String? = null,
    @SerializedName("price")
    val price: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("departmentId")
    val departmentId: String? = null,
)