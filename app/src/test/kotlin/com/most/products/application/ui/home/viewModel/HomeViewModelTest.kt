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
    fun `test getDepartments without id case success should return value`() {
        // arrange
        coEvery {
            departmentUseCase.execute()
        } returns flowOf(
            listOf(
                DepartmentDomainModel(
                    id = "1",
                    name = "most",
                    imageUrl = "imageUrl"
                )
            )
        )

        // act
        viewModel.getDepartments()

        // assert
        coVerify(exactly = 1) { departmentUseCase.execute() }
    }

    @Test
    fun `test getDepartments without id case error should thrown exception`() = runTest {
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
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }
}
