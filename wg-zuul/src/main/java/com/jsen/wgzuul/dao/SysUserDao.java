package com.jsen.wgzuul.dao;

import com.jsen.wgzuul.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @（#）:SecUserDao.java
 * @description:
 * @author: jsen 2018/8/12
 * @version: Version 1.0
 */
@Mapper
public interface SysUserDao {
    /**
     * 根据登录名查找用户信息
     *
     * @param loginName
     * @return
     */
    SysUser findUserByLoginName(@Param("loginName") String loginName);

}
