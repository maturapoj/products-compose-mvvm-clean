package com.most.products.application.domain.usecase

import com.most.products.application.core.network.model.ErrorException
import com.most.products.application.data.model.DepartmentResponse
import com.most.products.application.data.repository.DepartmentsRepository
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DepartmentUseCaseTest {

    private lateinit var useCase: DepartmentUseCase
    private val repository: DepartmentsRepository = mockk()

    @Before
    fun setUp() {
        useCase = DepartmentUseCase(
            departmentsRepository = repository
        )
    }

    @Test
    fun `test execute without id case success should return value`() = runTest {
        // arrange
        coEvery { repository.getDepartment(null) } returns flowOf(
            listOf(
                DepartmentResponse(
                    id = "1",
                    name = "name",
                    imageUrl = "imageUrl"
                )
            )
        )

        // act
        val result = useCase.execute()

        // assert
        result.collect {
            assertEquals("1", it[0].id)
            assertEquals("name", it[0].name)
        }
    }

    @Test
    fun `test execute with id case success should return value`() = runTest {
        // arrange
        coEvery { repository.getDepartment("1") } returns flowOf(
            listOf(
                DepartmentResponse(
                    id = "1",
                    name = "name",
                    imageUrl = "imageUrl",
                    desc = "desc",
                    price = "price",
                    type = "type",
                    departmentId = "1"
                )
            )
        )

        // act
        val result = useCase.execute("1")

        // assert
        result.collect {
            assertEquals("1", it[0].id)
            assertEquals("name", it[0].name)
        }
        verify(exactly = 1) { useCase.execute("1") }
    }

    @Test
    fun `test execute id is null case error should thrown exception`() = runTest {
        // arrange
        val mockStatusCode = "400"
        coEvery { repository.getDepartment(null) } returns flow {
            throw ErrorException.ApiErrorException("message", mockStatusCode)
        }

        // act
        val result = useCase.execute()

        // assert
        result.catch {
            assertTrue(it is ErrorException.ApiErrorException)
            assertEquals((it as ErrorException.ApiErrorException).statusCode, mockStatusCode)
        }
    }
}