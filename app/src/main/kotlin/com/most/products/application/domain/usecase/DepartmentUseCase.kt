package com.most.products.application.domain.usecase

import com.most.products.application.data.repository.DepartmentsRepository
import com.most.products.application.domain.mapper.toDomainModel
import com.most.products.application.domain.model.DepartmentDomainModel
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