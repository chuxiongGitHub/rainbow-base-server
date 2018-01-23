package com.rainbow.base.commons

import com.baomidou.mybatisplus.plugins.PaginationInterceptor
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class MybatisPlusConfig {


    @Bean
    @Profile("default")
    fun performanceInterceptor() = PerformanceInterceptor()


    @Bean
    fun paginationInterceptor() = PaginationInterceptor()
}