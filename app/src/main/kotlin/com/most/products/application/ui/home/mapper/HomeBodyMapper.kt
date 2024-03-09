package com.most.products.application.ui.home.mapper

import com.most.products.application.domain.model.DepartmentDomainModel
import com.most.products.application.ui.home.model.HomeBodyModel

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