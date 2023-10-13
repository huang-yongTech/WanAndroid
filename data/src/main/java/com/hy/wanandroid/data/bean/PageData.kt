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
class PageData<T> {
    var curPage = 0
    var datas: MutableList<T>? = null
    var offset = 0
    var over = false
    var pageCount = 0
    var size = 0
    var total = 0
}