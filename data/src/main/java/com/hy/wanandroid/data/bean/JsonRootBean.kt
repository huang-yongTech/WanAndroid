/**
 * Copyright 2020 bejson.com
 */
package com.hy.wanandroid.data.bean

/**
 * Auto-generated: 2020-03-25 17:45:9
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
class JsonRootBean<T> {
    var data: T? = null
        private set
    var errorCode = 0
    var errorMsg: String? = null
    fun setData(data: T) {
        this.data = data
    }
}