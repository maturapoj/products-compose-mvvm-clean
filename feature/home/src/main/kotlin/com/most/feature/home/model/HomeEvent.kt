package com.most.feature.home.model

sealed class HomeEvent {
    data class ShowDialog(val desc: String): HomeEvent()
}