package com.most.core.domain.mapper

import com.most.core.data.model.DepartmentResponse
import com.most.core.domain.model.DepartmentDomainModel


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
