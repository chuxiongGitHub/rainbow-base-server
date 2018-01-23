package com.rainbow.base.commons

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

open class SuperController<S : ISuperService<T>, T : SuperEntity> {

    @Suppress("SpringKotlinAutowiredMembers")
    @Autowired
    protected lateinit var baseService: S

    @GetMapping("/{id}")
    open fun get(@PathVariable id: String): T? = baseService.selectById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    open fun create(@RequestBody t: T) {
        baseService.insert(t)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    open fun update(@PathVariable id: String, @RequestBody t: T) {
        baseService.updateById(t.apply { this.id = id })
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    open fun delete(@PathVariable id: String) {
        baseService.deleteById(id)
    }

}