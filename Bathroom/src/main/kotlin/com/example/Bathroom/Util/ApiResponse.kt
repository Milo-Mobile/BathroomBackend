package com.example.Bathroom.Util
import org.springframework.stereotype.Component

@Component
data class ApiResponse<T>(
    var code: Int = 0,
    var message: String = "",
    var data: T? = null,
    var error: String? = null,
    var timestamp: Long = System.currentTimeMillis()
) {
    constructor(data: T) : this() {
        this.code = ReturnCode.RC200.code
        this.message = ReturnCode.RC200.message
        this.data = data
    }

    companion object {
        fun <T> success(data: T): ApiResponse<T> {
            return ApiResponse(data)
        }

        fun <T> error(code: Int, message: String): ApiResponse<T> {
            return ApiResponse<T>().apply {
                this.code = code
                this.message = message
            }
        }
    }
}

enum class ReturnCode(val code: Int, val message: String) {
    RC200(200, "OK"),
    // Add other return codes as needed
}
