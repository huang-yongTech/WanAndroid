package com.hy.wanandroid.data.bean

/**
 * author：created by huangyong on 2020/3/26 18:48
 * email：756655135@qq.com
 * description :
 */
class JsonListRootBean<T> {
    var data: MutableList<T>? = null
    var errorCode = 0
    var errorMsg: String? = null
}