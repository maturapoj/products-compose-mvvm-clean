package com.most.products.application.ui.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.most.products.application.core.dispatcher.DispatcherProviders
import com.most.products.application.domain.usecase.DepartmentsUseCase
import com.most.products.application.ui.home.model.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class HomeViewModel(
    private val departmentsUseCase: DepartmentsUseCase,
    private val dispatcherProviders: DispatcherProviders,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun getDepartments() {
        departmentsUseCase.execute()
            .flowOn(dispatcherProviders.io)
            .onEach { department ->
                _uiState.value = HomeUiState(
                    departments = department,
                )
            }
            .catch {
                println("Most catch : ${it}")
            }
            .launchIn(viewModelScope)
    }

    fun getProductId(id: String, departmentName: String) {
        departmentsUseCase.execute(id)
            .flowOn(dispatcherProviders.io)
            .onEach { products ->
                _uiState.update { currentState ->
                    currentState.copy(
                        products = products,
                        departmentName = departmentName
                    )
                }
            }
            .catch {
                println("Most catch : ${it}")
            }
            .launchIn(viewModelScope)
    }
}