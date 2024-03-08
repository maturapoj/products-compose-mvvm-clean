package com.most.products.application.domain.usecase

import com.most.products.application.data.repository.DepartmentsRepository
import com.most.products.application.domain.model.DepartmentModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DepartmentsUseCase(
    private val departmentsRepository: DepartmentsRepository,
) {

    fun execute(id: String? = null): Flow<List<DepartmentModel>> {
        return departmentsRepository.getDepartment(id).map {
            it.map { department ->
                DepartmentModel(
                    id = department.id.orEmpty(),
                    name = department.name.orEmpty(),
                    imageUrl = department.imageUrl.orEmpty(),
                    departmentId = department.departmentId,
                    desc = department.desc,
                    type = department.type,
                    price = department.price,
                )
            }
        }
    }
}