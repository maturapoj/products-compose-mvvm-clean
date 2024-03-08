package com.most.products.application.ui.home.viewModel

import com.most.products.application.core.dispatcher.DispatcherProviders
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TestDispatcherProvider : DispatcherProviders {
    override val default: CoroutineDispatcher
        get() = Dispatchers.Unconfined
    override val io: CoroutineDispatcher
        get() = Dispatchers.Unconfined
    override val main: CoroutineDispatcher
        get() = Dispatchers.Unconfined
    override val unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}