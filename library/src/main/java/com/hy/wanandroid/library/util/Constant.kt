package com.hy.wanandroid.library.util;

import java.util.Random;

/**
 * Created by huangyong on 2017/11/28.
 * 常量提供工具类
 */

public final class Constant {

    private Constant() {
    }

    /**
     * 颜色范围
     */
    public static final int COLOR_RGB_MIN = 20;
    public static final int COLOR_RGB_MAX = 230;

    /**
     * 界面跳转传值标识
     */
    public static final String TYPE = "type";
    /**
     * 界面跳转传值类型
     */
    public static final String TYPE_SEARCH_KEY = "type_search_key";

    /**
     * 附件id和附件名标识
     */
    public static final String ATTACHMENT_ID = "attachmentId";
    public static final String ATTACHMENT_NAME = "attachmentName";
    //上传的附件类型
    public static final String TYPE_DOC = ".doc";
    public static final String TYPE_DOCX = ".docx";
    public static final String TYPE_JPG = ".jpg";
    public static final String TYPE_PNG = ".png";
    public static final String TYPE_PDF = ".pdf";
    public static final String TYPE_PPTX = ".pptx";
    public static final String TYPE_TXT = ".txt";
    public static final String TYPE_XLSX = ".xlsx";
}
