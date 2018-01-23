package com.rainbow.base.commons

import com.baomidou.mybatisplus.annotations.TableField
import com.baomidou.mybatisplus.enums.FieldFill
import java.util.*

open class SuperEntity {

    /**
     * 数据库主键 20位长度
     */
    var id: String? = null

    /**
     * 状态
     */
    var status: Int? = null

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    var createTime: Date? = null


    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    var updateTime: Date? = null
}