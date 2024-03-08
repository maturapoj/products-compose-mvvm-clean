package com.most.products.application.ui.home.model

import androidx.compose.runtime.Immutable
import com.most.products.application.domain.model.DepartmentModel

@Immutable
data class HomeUiState(
    val departments: List<DepartmentModel>? = null,
    val products: List<DepartmentModel>? = null,
    val productsDesc : List<DepartmentModel>? = null,
    val departmentName: String? = null,
)