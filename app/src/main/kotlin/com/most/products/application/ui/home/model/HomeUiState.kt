package com.most.products.application.ui.home.model

import androidx.compose.runtime.Immutable

@Immutable
data class HomeUiState(
    val headerContent: List<HomeHeaderUiModel>? = null,
    val bodyContent: HomeBodyUiModel? = null,
    val openDialog : ((String) -> Unit)? = null,
    val isLoading: Boolean = false,
    val apiError: String? = null,
)
