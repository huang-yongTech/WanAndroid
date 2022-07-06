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
class Article {
    var apkLink: String? = null
    var audit = 0
    var author: String? = null
    var canEdit = false
    var chapterId = 0
    var chapterName: String? = null
    var collect = false
    var courseId = 0
    var desc: String? = null
    var descMd: String? = null
    var envelopePic: String? = null
    var fresh = false
    var id = 0
    var link: String? = null
    var niceDate: String? = null
    var niceShareDate: String? = null
    var origin: String? = null
    var prefix: String? = null
    var projectLink: String? = null
    var publishTime: Long = 0
    var selfVisible = 0
    var shareDate: Long = 0
    var shareUser: String? = null
    var superChapterId = 0
    var superChapterName: String? = null
    var tags: List<Version>? = null
    var title: String? = null
        get() {
            return when {
                field?.contains("</em>") == true ->
                    field?.split("</em>")?.get(1)
                field?.contains("&mdash;") == true ->
                    field?.replace("&mdash;", "-")
                else -> return field
            }
        }
    var type = 0
    var userId = 0
    var visible = 0
    var zan = 0
    override fun toString(): String {
        return "Article{" +
                "apkLink='" + apkLink + '\'' +
                ", audit=" + audit +
                ", author='" + author + '\'' +
                ", canEdit=" + canEdit +
                ", chapterId=" + chapterId +
                '}'
    }
}