package com.jsen.wgzuul.dao;

import com.jsen.wgzuul.entity.SecUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @（#）:SecUserDao.java
 * @description:
 * @author: jsen 2018/8/12
 * @version: Version 1.0
 */
@Mapper
public interface SecUserDao {
    /**
     * 根据登录名查找用户信息
     *
     * @param loginName
     * @return
     */
    SecUser findUserByLoginName(@Param("loginName") String loginName,@Param("state") Integer state);

}
