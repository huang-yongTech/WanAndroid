package com.hy.wanandroid.data.bean

import androidx.room.*

/**
 * author：created by huangyong on 2020/3/26 18:49
 * email：756655135@qq.com
 * description :
 */
@Entity(tableName = "hot_word")
class HotWord {
    var id = 0
    var link: String? = null

    @PrimaryKey
    @ColumnInfo
    var name = ""
    var order = 0
    var visible = 0

    constructor() {}

    @Ignore
    constructor(name: String) {
        this.name = name
    }
}