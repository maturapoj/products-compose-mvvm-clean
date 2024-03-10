package com.most.feature.home.mapper

import com.most.core.domain.model.DepartmentDomainModel
import com.most.feature.home.model.HomeBodyModel

fun List<DepartmentDomainModel>.toBodyUiModel() : List<HomeBodyModel> = map {
    HomeBodyModel(
        id = it.id,
        name = it.name,
        imageUrl = it.imageUrl,
        departmentId = it.departmentId.orEmpty(),
        desc = it.desc.orEmpty(),
        type = it.type.orEmpty(),
        price = it.price.orEmpty(),
    )
}