package com.most.products.application.domain.mapper

import com.most.products.application.data.model.DepartmentResponse
import com.most.products.application.domain.model.DepartmentDomainModel

fun List<DepartmentResponse>.toDomainModel(): List<DepartmentDomainModel> = map { department ->
    DepartmentDomainModel(
        id = department.id.orEmpty(),
        name = department.name.orEmpty(),
        imageUrl = department.imageUrl.orEmpty(),
        departmentId = department.departmentId,
        desc = department.desc,
        type = department.type,
        price = department.price,
    )
}
