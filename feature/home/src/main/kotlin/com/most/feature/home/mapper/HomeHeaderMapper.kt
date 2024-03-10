package com.most.feature.home.mapper

import com.most.core.domain.model.DepartmentDomainModel
import com.most.feature.home.model.HomeHeaderUiModel

fun List<DepartmentDomainModel>.toHeaderUiModel() : List<HomeHeaderUiModel> = map {
    HomeHeaderUiModel(
        id = it.id,
        name = it.name,
        imageUrl = it.imageUrl,
    )
}
