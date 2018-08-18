package com.jsen.wgzuul.service;

import com.jsen.wgzuul.dao.SecRoleDao;
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
    private SecRoleDao secRoleDao;

    public List<String> findRoleListByUserId(Integer userId){
        return secRoleDao.findRoleListByUserId(userId);
    }
}
