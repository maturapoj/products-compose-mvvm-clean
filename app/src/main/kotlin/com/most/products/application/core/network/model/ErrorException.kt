package com.most.products.application.core.network.model

sealed class ErrorException(
    msg: String?,
    open val statusCode: String? = null,
) : Exception(msg) {

    data class ApiErrorException(
        val msg: String,
        override val statusCode: String,
    ) : ErrorException(msg, statusCode)
}