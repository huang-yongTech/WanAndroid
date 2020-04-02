package com.hy.wanandroid.data.bean;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * author：created by huangyong on 2020/3/26 18:49
 * email：756655135@qq.com
 * description :
 */
@Entity(tableName = "hot_word")
public class HotWord {
    private int id;
    private String link;
    @PrimaryKey
    @NonNull
    @ColumnInfo
    private String name = "";
    private int order;
    private int visible;

    public HotWord() {
    }

    @Ignore
    public HotWord(@NonNull String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public int getVisible() {
        return visible;
    }
}
