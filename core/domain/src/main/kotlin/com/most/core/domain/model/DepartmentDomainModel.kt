package com.most.core.domain.model

data class DepartmentDomainModel(
    val id: String,
    val name: String,
    val imageUrl: String,
    val departmentId: String? = null,
    val desc: String? = null,
    val type: String? = null,
    val price: String? = null,
)
