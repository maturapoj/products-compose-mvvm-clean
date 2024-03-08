package com.most.products.application.ui.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.most.products.application.core.dispatcher.DispatcherProviders
import com.most.products.application.domain.usecase.DepartmentsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeViewModel(
    private val departmentsUseCase: DepartmentsUseCase,
    private val dispatcherProviders: DispatcherProviders,
) : ViewModel() {

    init {
        getDepartments()
    }

    val departmentsItem: MutableStateFlow<String> = MutableStateFlow("")

    fun getDepartments() {
        departmentsUseCase.execute()
            .flowOn(dispatcherProviders.io)
            .onEach {
                println("Most onEach department: ${it}")
                departmentsItem.value = "Most onEach"
                getProductId("1")
            }
            .catch {
                println("Most catch : ${it}")
                departmentsItem.value = "Most catch"
            }
            .launchIn(viewModelScope)
    }

    fun getProductId(id: String) {
        departmentsUseCase.execute(id)
            .flowOn(dispatcherProviders.io)
            .onEach {
                println("Most onEach department: ${it}")
                departmentsItem.value = "Most onEach"
            }
            .catch {
                println("Most catch : ${it}")
                departmentsItem.value = "Most catch"
            }
            .launchIn(viewModelScope)
    }
}