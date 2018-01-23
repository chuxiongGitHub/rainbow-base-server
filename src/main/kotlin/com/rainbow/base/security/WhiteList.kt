package com.rainbow.base.security

/**
 * 跳过拦截器配置
 */
object WhiteList {
    val list = arrayOf("/", "/favicon.ico", "/druid/**", "/swagger/**", "/webjars/**", "/static/**")

    fun check(uri: String) = list.any {
        if (it.endsWith("**")) {
            uri.startsWith(it.substringBefore("**"))
        } else {
            uri == it
        }
    }
}