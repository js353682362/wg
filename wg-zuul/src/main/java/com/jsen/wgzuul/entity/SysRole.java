package com.jsen.wgzuul.entity;

import lombok.Data;

/**
 * @（#）:SecRole.java
 * @description:
 * @author: jsen 2018/8/12
 * @version: Version 1.0
 */
@Data
public class SysRole {
    private Integer id;

    private String role;

    private String name;

    private String modules;

    private String describe;
}
