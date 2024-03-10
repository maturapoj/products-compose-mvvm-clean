package com.most.core.data.repository

import com.most.core.data.model.DepartmentResponse
import kotlinx.coroutines.flow.Flow

interface DepartmentsRepository {
    fun getDepartment(id: String?) : Flow<List<DepartmentResponse>>
}
