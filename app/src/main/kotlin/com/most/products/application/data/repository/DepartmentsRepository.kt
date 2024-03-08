package com.most.products.application.data.repository

import com.most.products.application.data.model.DepartmentResponse
import kotlinx.coroutines.flow.Flow

interface DepartmentsRepository {
    fun getDepartment(id: String?) : Flow<List<DepartmentResponse>>
}
