package com.rainbow.base.beetl

import org.springframework.boot.context.properties.ConfigurationProperties
import java.util.*


@ConfigurationProperties(prefix = "beetl")
class BeetlProperties {

    var suffix = ".html"
    var contentType = "text/html;charset=UTF-8"
    var baseRoot = "/template"
    var properties = Properties()

}