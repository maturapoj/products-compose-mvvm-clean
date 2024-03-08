package com.most.products.application.ui.home.viewModel

import com.most.products.application.core.network.model.ErrorException
import com.most.products.application.domain.model.DepartmentModel
import com.most.products.application.domain.usecase.DepartmentsUseCase
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
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private val departmentsUseCase: DepartmentsUseCase = mockk()
    private val dispatcherProviders = TestDispatcherProvider()

    @Before
    fun setUp() {
        viewModel = HomeViewModel(
            departmentsUseCase = departmentsUseCase,
            dispatcherProviders = dispatcherProviders
        )
        Dispatchers.setMain(dispatcherProviders.io)
    }

    @Test
    fun `test getDepartments without id case success should return value`() {
        // arrange
        coEvery {
            departmentsUseCase.execute()
        } returns flowOf(
            listOf(
                DepartmentModel(
                    id = "1",
                    name = "most",
                    imageUrl = "imageUrl"
                )
            )
        )

        // act
        viewModel.getDepartments()

        // assert
        coVerify(exactly = 1) { departmentsUseCase.execute() }
    }

    @Test
    fun `test getDepartments without id case error should thrown exception`() = runTest {
        // arrange
        coEvery {
            departmentsUseCase.execute()
        } returns flow {
            throw ErrorException.ApiErrorException("msg", "400")
        }

        // act
        viewModel.getDepartments()

        // assert
        coVerify(exactly = 1) { departmentsUseCase.execute() }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }
}
