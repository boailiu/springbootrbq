package com.boai.springbootrbq.Model;

import com.alibaba.fastjson.annotation.JSONField;

public class Inner {

    @JSONField(name = "ID")
    private int id;
    @JSONField(name = "CONTENT")
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
