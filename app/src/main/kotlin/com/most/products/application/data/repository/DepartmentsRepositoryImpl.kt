package com.most.products.application.data.repository

import com.most.products.application.core.network.model.ErrorException
import com.most.products.application.data.api.ApiService
import com.most.products.application.data.model.DepartmentResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class DepartmentsRepositoryImpl(
    private val service: ApiService,
) : DepartmentsRepository {

    override fun getDepartment(id: String?): Flow<List<DepartmentResponse>> {
        return flow {
            val response: Response<List<DepartmentResponse>> = when (id) {
                null -> service.getDepartments()
                else -> service.getDepartmentWithId(id)
            }
            if (response.isSuccessful) {
                response.body()?.also {
                    emit(it)
                }
            } else {
                throw ErrorException.ApiErrorException(
                    msg = response.errorBody().toString(),
                    statusCode = response.code().toString()
                )
            }
        }
    }
}
