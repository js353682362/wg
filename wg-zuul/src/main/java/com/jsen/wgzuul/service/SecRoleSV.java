package com.jsen.wgzuul.service;

import com.jsen.wgzuul.dao.SysRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @（#）:SecRoleSV.java
 * @description:
 * @author: jsen 2018/8/12
 * @version: Version 1.0
 */
@Service
public class SecRoleSV {
    @Autowired
    private SysRoleDao sysRoleDao;

    public List<String> findRoleListByUserId(Integer userId){
        return sysRoleDao.findRoleListByUserId(userId);
    }
}
