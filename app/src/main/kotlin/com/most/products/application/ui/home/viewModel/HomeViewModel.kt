package com.most.products.application.ui.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.most.products.application.core.dispatcher.DispatcherProviders
import com.most.products.application.domain.model.DepartmentDomainModel
import com.most.products.application.domain.usecase.DepartmentUseCase
import com.most.products.application.ui.home.mapper.toHeaderUiModel
import com.most.products.application.ui.home.mapper.toBodyUiModel
import com.most.products.application.ui.home.model.HomeBodyUiModel
import com.most.products.application.ui.home.model.HomeEvent
import com.most.products.application.ui.home.model.HomeUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val departmentUseCase: DepartmentUseCase,
    private val dispatcherProviders: DispatcherProviders,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val eventChannel = Channel<HomeEvent>(Channel.BUFFERED)
    val event = eventChannel.receiveAsFlow()

    fun getDepartments() {
        departmentUseCase.execute()
            .flowOn(dispatcherProviders.io)
            .onEach {
                _uiState.value = HomeUiState(
                    headerContent = it.toHeaderUiModel()
                )
            }
            .catch {
                println("Most catch : ${it}")
            }
            .launchIn(viewModelScope)
    }

    fun getProductId(id: String, departmentName: String) {
        departmentUseCase.execute(id)
            .flowOn(dispatcherProviders.io)
            .onEach { products: List<DepartmentDomainModel> ->
                _uiState.update { currentState ->
                    currentState.copy(
//                        products = products.toUiModel(),
                        bodyContent = HomeBodyUiModel(
                            departmentName = departmentName,
                            bodyModel = products.toBodyUiModel()
                        ),
//                        departmentName = departmentName,
                        openDialog = {
                            publishEvent(HomeEvent.ShowDialog(it))
                        }
                    )
                }
            }
            .catch {
                println("Most catch : ${it}")
            }
            .launchIn(viewModelScope)
    }

    private fun publishEvent(event: HomeEvent) {
        viewModelScope.launch {
            eventChannel.send(event)
        }
    }
}