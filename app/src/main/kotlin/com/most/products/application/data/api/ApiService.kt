package com.most.products.application.data.api

import com.most.products.application.data.URLConstants
import com.most.products.application.data.model.DepartmentResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(URLConstants.DEPARTMENTS)
    suspend fun getDepartments(): Response<List<DepartmentResponse>>

    @GET(URLConstants.DEPARTMENTS_ID)
    suspend fun getDepartmentWithId(
        @Path("id") id: String
    ): Response<List<DepartmentResponse>>
}