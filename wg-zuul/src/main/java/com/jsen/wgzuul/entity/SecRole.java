package com.jsen.wgzuul.entity;

/**
 * @（#）:SecRole.java
 * @description:
 * @author: jsen 2018/8/12
 * @version: Version 1.0
 */
public class SecRole {
    private Integer id;

    private String roleName;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
