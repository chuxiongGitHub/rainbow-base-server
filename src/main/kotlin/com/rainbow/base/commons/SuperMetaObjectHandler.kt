package com.rainbow.base.commons

import com.baomidou.mybatisplus.mapper.MetaObjectHandler
import com.baomidou.mybatisplus.toolkit.IdWorker
import org.apache.ibatis.reflection.MetaObject
import java.util.*

open class SuperMetaObjectHandler : MetaObjectHandler() {

    override fun insertFill(metaObject: MetaObject?) {
        setFieldValByName("id", IdWorker.getId().toString(), metaObject)
        setFieldValByName("createTime", Date(), metaObject)
    }

    override fun updateFill(metaObject: MetaObject?) {
        setFieldValByName("updateTime", Date(), metaObject)
    }
}