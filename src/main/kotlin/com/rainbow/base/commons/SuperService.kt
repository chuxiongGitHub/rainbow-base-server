package com.rainbow.base.commons

import com.baomidou.mybatisplus.mapper.BaseMapper
import com.baomidou.mybatisplus.service.impl.ServiceImpl
import java.io.Serializable

open class SuperService<M : BaseMapper<T>, T : SuperEntity> : ServiceImpl<M, T>(), ISuperService<T> {

    /**
     * 根据id删除
     */
    override fun deleteById(id: Serializable?): Boolean {

        val current = selectById(id)
        if (beforeDelete(current)) {
            val result = super.deleteById(id)
            return result
        }
        return false
    }

    override fun selectById(id: Serializable?): T {
        return super.selectById(id)

    }


    /**
     * 根据id更新数据
     */
    override fun updateById(entity: T): Boolean {
        val current = selectById(entity.id)

        if (beforeUpdate(current, entity)) {
            val result = super.updateById(entity)

            return result
        }
        return false
    }

    /**
     * 批量新增数据
     */
    override fun insertBatch(entityList: List<T>, batchSize: Int): Boolean {

        if (beforeInsertBatch(entityList)) {
            val result = super.insertBatch(entityList, batchSize)
            if (result) afterInsertBatch(entityList)
        }
        return false
    }

    /**
     * 批量新增数据
     */
    override fun insertBatch(entityList: List<T>): Boolean {
        if (beforeInsertBatch(entityList)) {
            val result = super.insertBatch(entityList)
            if (result) afterInsertBatch(entityList)
        }
        return false
    }

    override fun beforeInsert(entity: T): Boolean {
        entity.status = entity.status ?: 1
        return true
    }

    override fun beforeInsertBatch(entityList: List<T>): Boolean {

        entityList.forEach { it.status = it.status ?: 1 }
        return true
    }

    override fun beforeUpdate(current: T?, entity: T): Boolean {
        current ?: throw AppError("未找到记录,无法更新")
        return true
    }

    override fun beforeDelete(current: T?): Boolean {
        current ?: throw AppError("未找到记录,无法更新")
        return true
    }

    override fun afterInsert(entity: T) {}

    override fun afterInsertBatch(entityList: List<T>) {}

    override fun afterUpdate(entity: T) {}

    override fun afterDelete(current: T) {}
}