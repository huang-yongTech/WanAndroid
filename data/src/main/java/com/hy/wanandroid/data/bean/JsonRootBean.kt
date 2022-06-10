/**
 * Copyright 2020 bejson.com
 */
package com.hy.wanandroid.data.bean;

/**
 * Auto-generated: 2020-03-25 17:45:9
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean<T> {

    private T data;
    private int errorCode;
    private String errorMsg;

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
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