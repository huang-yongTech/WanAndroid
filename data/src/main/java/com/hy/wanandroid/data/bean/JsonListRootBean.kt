package com.hy.wanandroid.data.bean;

import java.util.List;

/**
 * author：created by huangyong on 2020/3/26 18:48
 * email：756655135@qq.com
 * description :
 */
public class JsonListRootBean<T> {

    private List<T> data;
    private int errorCode;
    private String errorMsg;

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
