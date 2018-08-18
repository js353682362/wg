package com.jsen.wgzuul.dao;

/**
 * @（#）:SecRoleUserRel.java
 * @description:
 * @author: jsen 2018/8/12
 * @version: Version 1.0
 */
public class SecRoleUserRel {
    private Integer id;

    private Integer roleId;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
