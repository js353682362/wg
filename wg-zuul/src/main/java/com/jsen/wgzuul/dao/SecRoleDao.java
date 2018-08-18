package com.jsen.wgzuul.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @（#）:SecRoleDao.java
 * @description:
 * @author: jsen 2018/8/12
 * @version: Version 1.0
 */
@Mapper
public interface SecRoleDao {

    /**
     * 查找用户角色列表
     * @param userId
     * @return
     */
    List<String> findRoleListByUserId(Integer userId);
}
