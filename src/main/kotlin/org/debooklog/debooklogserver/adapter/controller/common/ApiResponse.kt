package org.debooklog.debooklogserver.adapter.controller.common

data class ApiResponse<T>(
    val message: String? = "success",
    val data: T? = null,
) {
    companion object {
        fun <T> of(data: T): ApiResponse<T> {
            return ApiResponse(data = data)
        }

        fun error(message: String?): ApiResponse<Unit> = ApiResponse(message = message)

        fun empty(): ApiResponse<Unit> {
            return ApiResponse(data = null)
        }
    }
}
