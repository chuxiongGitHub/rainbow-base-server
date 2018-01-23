package com.rainbow.base.beetl

import org.beetl.core.resource.FileResourceLoader
import org.beetl.ext.spring.BeetlGroupUtilConfiguration
import org.beetl.ext.spring.BeetlSpringViewResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
@EnableConfigurationProperties(BeetlProperties::class)
class BeetlConfig {

    @Autowired
    private lateinit var properties: BeetlProperties


    @Bean(initMethod = "init")
    fun getBeetlGroupUtilConfiguration(): BeetlGroupUtilConfiguration {
        val config = BeetlGroupUtilConfiguration()
        config.setResourceLoader(FileResourceLoader(properties.baseRoot, Charsets.UTF_8.name()))
        config.setConfigProperties(properties.properties)
        return config
    }

    @Bean
    fun getBeetlSpringViewResolver(): BeetlSpringViewResolver {
        val beetlSpringViewResolver = BeetlSpringViewResolver()
        beetlSpringViewResolver.setSuffix(properties.suffix)
        beetlSpringViewResolver.setContentType(properties.contentType)
        beetlSpringViewResolver.order = 0
        beetlSpringViewResolver.config = getBeetlGroupUtilConfiguration()
        return beetlSpringViewResolver
    }
}