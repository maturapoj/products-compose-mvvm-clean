package com.most.products.application.ui.home.model

sealed class HomeEvent {
    data class ShowDialog(val desc: String): HomeEvent()
}