package com.rainbow.base.commons

import feign.FeignException
import org.slf4j.LoggerFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletResponse

@RestControllerAdvice
class ErrorHandler {
    private val logger by lazy { LoggerFactory.getLogger(ErrorHandler::class.java) }

    @ExceptionHandler
    fun handler(ex: Exception, response: HttpServletResponse) {

        val raw = Tools.getRawError(ex)

        when (raw) {
            is AuthenticationException,
            is AccessDeniedException -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, raw.message)

            is AppError, is HttpRequestMethodNotSupportedException ->
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, raw.message)

            is IllegalArgumentException -> {
                val params = raw.message?.split(" parameter ")
                if (params?.size == 2) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "请求参数 ${params[1]} 不能为空")
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "请求参数不能为空")
                }
            }

            is FeignException ->
                response.sendError(raw.status(), raw.message)

            else -> {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, raw.message)
                logger.error(ex.message, ex)
            }
        }
    }
}