package com.most.products.application.domain.model

data class DepartmentModel(
    val id: String,
    val name: String,
    val imageUrl: String,
    val departmentId: String? = null,
    val desc: String? = null,
    val type: String? = null,
    val price: String? = null,
)
