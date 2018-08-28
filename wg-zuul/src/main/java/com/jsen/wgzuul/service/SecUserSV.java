package com.jsen.wgzuul.service;

import com.jsen.wgzuul.dao.SysUserDao;
import com.jsen.wgzuul.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @（#）:SecUserSV.java
 * @description:
 * @author: jsen 2018/8/12
 * @version: Version 1.0
 */
@Service
public class SecUserSV {

    @Autowired
    private SysUserDao sysUserDao;

    public SysUser findByLoginname(String loginName) {
        return sysUserDao.findUserByLoginName(loginName);
    }
}
