package com.most.products.application.ui.home.model

import java.util.UUID

data class HomeBodyUiModel(
    val departmentName: String,
    val bodyModel: List<HomeBodyModel>,
)

data class HomeBodyModel(
    val keyId: String = UUID.randomUUID().toString(),
    val id: String,
    val name: String,
    val imageUrl: String,
    val departmentId: String,
    val desc: String,
    val type: String,
    val price: String,
)