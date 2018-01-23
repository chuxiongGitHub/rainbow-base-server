package com.rainbow.base.commons

open class AppError(override val message: String) : Exception(message) {
}