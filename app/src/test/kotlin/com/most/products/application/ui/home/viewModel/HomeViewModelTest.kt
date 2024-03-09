package com.most.products.application.ui.home.viewModel

import com.most.products.application.core.network.model.ErrorException
import com.most.products.application.domain.model.DepartmentDomainModel
import com.most.products.application.domain.usecase.DepartmentUseCase
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private val departmentUseCase: DepartmentUseCase = mockk()
    private val dispatcherProviders = TestDispatcherProvider()

    @Before
    fun setUp() {
        viewModel = HomeViewModel(
            departmentUseCase = departmentUseCase,
            dispatcherProviders = dispatcherProviders
        )
        Dispatchers.setMain(dispatcherProviders.io)
    }

    @Test
    fun `test getDepartments case success should return value`() {
        // arrange
        val mockModel = listOf(
            DepartmentDomainModel(
                id = "1",
                name = "movies",
                imageUrl = "imageUrl"
            )
        )
        coEvery {
            departmentUseCase.execute()
        } returns flowOf(mockModel)

        // act
        viewModel.getDepartments()

        // assert
        coVerify(exactly = 1) { departmentUseCase.execute() }
        assertEquals("1", viewModel.uiState.value.headerContent?.get(0)?.id.orEmpty())
        assertEquals("movies", viewModel.uiState.value.headerContent?.get(0)?.name.orEmpty())
        assertEquals("imageUrl", viewModel.uiState.value.headerContent?.get(0)?.imageUrl.orEmpty())
        assertNull(viewModel.uiState.value.bodyContent)
    }

    @Test
    fun `test getDepartments case error should thrown exception`() = runTest {
        // arrange
        coEvery {
            departmentUseCase.execute()
        } returns flow {
            throw ErrorException.ApiErrorException("msg", "400")
        }

        // act
        viewModel.getDepartments()

        // assert
        coVerify(exactly = 1) { departmentUseCase.execute() }
        assertNull(viewModel.uiState.value.headerContent)
    }

    @Test
    fun `test getProductId case success should return value`() {
        // arrange
        val mockModel = listOf(
            DepartmentDomainModel(
                id = "1",
                name = "movies",
                imageUrl = "imageUrl",
                departmentId = "1",
                desc = "desc",
                type = "type",
                price = "price"
            )
        )
        coEvery {
            departmentUseCase.execute("1")
        } returns flowOf(mockModel)

        // act
        viewModel.getProductId("1", "movies")

        // assert
        coVerify(exactly = 1) { departmentUseCase.execute("1") }
        assertEquals("movies", viewModel.uiState.value.bodyContent?.departmentName)
        assertNotNull(viewModel.uiState.value.bodyContent)
        assertNotNull(viewModel.uiState.value.openDialog)
    }

    @Test
    fun `test getProductId case error should thrown exception`() {
        // arrange
        coEvery {
            departmentUseCase.execute("1")
        } returns flow {
            throw ErrorException.ApiErrorException("msg", "400")
        }

        // act
        viewModel.getProductId("1", "movies")

        // assert
        coVerify(exactly = 1) { departmentUseCase.execute("1") }
        assertNull(viewModel.uiState.value.bodyContent)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }
}
