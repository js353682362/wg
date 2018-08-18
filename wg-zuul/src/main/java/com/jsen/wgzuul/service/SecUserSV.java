package com.jsen.wgzuul.service;

import com.jsen.wgcommon.constant.WgCommonConst;
import com.jsen.wgzuul.dao.SecUserDao;
import com.jsen.wgzuul.entity.SecUser;
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
    private SecUserDao secUserDao;

    public SecUser findByLoginname(String loginName) {
        return secUserDao.findUserByLoginName(loginName, WgCommonConst.TABLE.STATE_VALID);
    }
}
