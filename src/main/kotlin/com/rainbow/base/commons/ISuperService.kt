package com.rainbow.base.commons

import com.baomidou.mybatisplus.service.IService

interface ISuperService<T> : IService<T> {

    fun beforeInsert(entity: T): Boolean

    fun beforeInsertBatch(entityList: List<T>): Boolean

    fun beforeUpdate(current: T?, entity: T): Boolean

    fun beforeDelete(current: T?): Boolean

    fun afterInsert(entity: T)

    fun afterInsertBatch(entityList: List<T>)

    fun afterUpdate(entity: T)

    fun afterDelete(current: T)
}