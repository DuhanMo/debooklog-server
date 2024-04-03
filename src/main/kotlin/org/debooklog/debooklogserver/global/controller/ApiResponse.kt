package org.debooklog.debooklogserver.global.controller

data class ApiResponse<T>(
    val message: String = "success",
    val data: T? = null,
) {
    companion object {
        fun <T> of(data: T): ApiResponse<T> {
            return ApiResponse(data = data)
        }
    }
}
