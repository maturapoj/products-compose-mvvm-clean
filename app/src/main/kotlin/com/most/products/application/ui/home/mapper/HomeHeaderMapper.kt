package com.most.products.application.ui.home.mapper

import com.most.products.application.domain.model.DepartmentDomainModel
import com.most.products.application.ui.home.model.HomeHeaderUiModel

fun List<DepartmentDomainModel>.toHeaderUiModel() : List<HomeHeaderUiModel> = map {
    HomeHeaderUiModel(
        id = it.id,
        name = it.name,
        imageUrl = it.imageUrl,
    )
}
