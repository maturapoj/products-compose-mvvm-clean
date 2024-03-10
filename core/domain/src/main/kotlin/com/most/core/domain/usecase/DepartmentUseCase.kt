package com.most.core.domain.usecase

import com.most.core.data.repository.DepartmentsRepository
import com.most.core.domain.mapper.toDomainModel
import com.most.core.domain.model.DepartmentDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DepartmentUseCase(
    private val departmentsRepository: DepartmentsRepository,
) {

    fun execute(id: String? = null): Flow<List<DepartmentDomainModel>> {
        return departmentsRepository.getDepartment(id).map {
            it.toDomainModel()
        }
    }
}