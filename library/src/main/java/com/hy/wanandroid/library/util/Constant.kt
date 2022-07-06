package com.hy.wanandroid.library.util

/**
 * Created by huangyong on 2017/11/28.
 * 常量提供工具类
 */
object Constant {
    /**
     * 颜色范围
     */
    const val COLOR_RGB_MIN = 20
    const val COLOR_RGB_MAX = 230

    /**
     * 界面跳转传值标识
     */
    const val TYPE = "type"

    /**
     * 界面跳转传值类型
     */
    const val TYPE_SEARCH_KEY = "type_search_key"

    /**
     * 附件id和附件名标识
     */
    const val ATTACHMENT_ID = "attachmentId"
    const val ATTACHMENT_NAME = "attachmentName"

    //上传的附件类型
    const val TYPE_DOC = ".doc"
    const val TYPE_DOCX = ".docx"
    const val TYPE_JPG = ".jpg"
    const val TYPE_PNG = ".png"
    const val TYPE_PDF = ".pdf"
    const val TYPE_PPTX = ".pptx"
    const val TYPE_TXT = ".txt"
    const val TYPE_XLSX = ".xlsx"

    /**
     * 错误码名称
     *
     * @author nanHuang
     */
    object ERROR_NAME {
        const val SSL_ERROR = "网络连接失败"
        const val UNKNOWEN_ERROR = "未知的错误，请联系管理员！"
        const val HTTP_401_ERROR = "登录失效，需要重新登录"
        const val CONNECT_ERROR = "服务端连接错误！"
        const val SOCKET_ERROR = "无网络连接！"
        const val TIMEOUT_ERROR = "服务器连接超时，请检查网络！"
        const val NET_NOT_CONNECT = "网络未连接，请先连接网络"
        const val SYSTEM_ERROR = "系统异常，请联系管理员！"
    }
}