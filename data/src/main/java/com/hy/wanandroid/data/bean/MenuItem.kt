package com.hy.wanandroid.data.bean

import androidx.annotation.DrawableRes

/**
 * author：created by huangyong on 2020/3/31 14:46
 * email：756655135@qq.com
 * description :
 */
class MenuItem {
    var iconId = 0
    var title: String? = null

    constructor() {}
    constructor(@DrawableRes iconId: Int, title: String?) {
        this.iconId = iconId
        this.title = title
    }
}