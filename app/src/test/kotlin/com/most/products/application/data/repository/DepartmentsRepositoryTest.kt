package com.most.products.application.data.repository

import com.most.products.application.core.network.model.ErrorException
import com.most.products.application.data.api.ApiService
import com.most.products.application.data.model.DepartmentResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class DepartmentsRepositoryTest {

    private lateinit var repository: DepartmentsRepository
    private val service: ApiService = mockk()

    @Before
    fun setUp() {
        repository = DepartmentsRepositoryImpl(
            service = service
        )
    }

    @Test
    fun `test getDepartment without id case call api success should emit value`() = runTest {
        // arrange
        coEvery {
            service.getDepartments()
        } returns Response.success(
            listOf(
                DepartmentResponse(
                    id = "1",
                    name = "name",
                    imageUrl = "imageUrl",
                )
            )
        )

        // act
        val result = runCatching {
            repository.getDepartment(null)
        }

        // assert
        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull() is Flow<List<DepartmentResponse>>)
        result.getOrNull()?.map {
            assertEquals("1", it[0].id)
            assertEquals("name", it[0].name)
            assertEquals("imageUrl", it[0].imageUrl)
            assertEquals(null, it[0].price)
            assertEquals(null, it[0].type)
            assertEquals(null, it[0].departmentId)
        }
    }

    @Test
    fun `test getDepartment with id case call api success should emit value`() = runTest {
        // arrange
        coEvery {
            service.getDepartmentWithId("1")
        } returns Response.success(
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
        val result = runCatching {
            repository.getDepartment("1")
        }

        // assert
        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull() is Flow<List<DepartmentResponse>>)
        result.getOrNull()?.map {
            assertEquals("desc", it[0].desc)
            assertEquals("price", it[0].price)
            assertEquals("type", it[0].type)
            assertEquals("1", it[0].departmentId)
        }
    }

    @Test
    fun `test getDepartment with id case call api fail should thrown Exception`() = runTest {
        // arrange
        val mockId = "1"
        coEvery {
            service.getDepartmentWithId(mockId)
        } returns Response.error(
            400,
            "".toResponseBody(null)
        )

        // act
        val result = repository.getDepartment(mockId)

        // assert
        result.catch {
            assertTrue(it is ErrorException.ApiErrorException)
        }
    }
}