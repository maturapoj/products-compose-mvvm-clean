package com.most.products.application.ui.home.model

import java.util.UUID

data class HomeHeaderUiModel(
    val keyId: String = UUID.randomUUID().toString(),
    val id: String,
    val name: String,
    val imageUrl: String,
)