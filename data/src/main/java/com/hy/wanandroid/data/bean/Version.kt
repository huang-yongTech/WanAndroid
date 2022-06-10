package com.hy.wanandroid.data.bean;

/**
 * author：created by huangyong on 2020/3/30 18:41
 * email：756655135@qq.com
 * description :
 */
public class Version {
    private int versionCode;
    private String versionName;
    private String url;
    private String versionDesc;

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersionDesc() {
        return versionDesc;
    }

    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }
}
