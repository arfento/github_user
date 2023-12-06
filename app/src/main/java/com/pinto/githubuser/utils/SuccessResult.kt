package com.pinto.githubuser.utils

data class SuccessResult<out T>(
    val status: Status,
    val data: T?,
    val message: String?
) {

    companion object {

        fun <T> success(data: T?): SuccessResult<T> {
            return SuccessResult(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): SuccessResult<T> {
            return SuccessResult(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): SuccessResult<T> {
            return SuccessResult(Status.LOADING, data, null)
        }

    }
}
