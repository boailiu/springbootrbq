package com.boai.springbootrbq.Model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class Person {

    @JSONField(name = "ID")
    private int id;
    @JSONField(name = "NAME")
    private String name;
    @JSONField(name = "INNERS")
    private List<Inner> inners;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Inner> getInners() {
        return inners;
    }

    public void setInners(List<Inner> inners) {
        this.inners = inners;
    }
}
