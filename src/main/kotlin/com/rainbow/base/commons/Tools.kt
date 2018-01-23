package com.rainbow.base.commons


import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.converter.FormHttpMessageConverter
import org.springframework.http.converter.StringHttpMessageConverter
import java.util.*

object Tools {
    val objectMapper by lazy {
        jacksonObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setTimeZone(TimeZone.getTimeZone("GMT+8"))!!
    }

    val xmlMapper by lazy {
        XmlMapper().registerKotlinModule()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setTimeZone(TimeZone.getTimeZone("GMT+8"))!!
    }

    val restTemplate by lazy {
        RestTemplateBuilder()
                .messageConverters(StringHttpMessageConverter(Charsets.UTF_8), FormHttpMessageConverter())
                .build()!!
    }

    fun getRawError(ex: Throwable): Throwable {
        if (ex is AppError) return ex
        val child = ex.cause ?: return ex
        if (child == ex) return ex
        return getRawError(child)
    }
}