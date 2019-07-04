package com.swing.jxc.demo.swing.service;

import com.swing.jxc.demo.swing.model.UserInfo;

public interface UserService {

    /**
     * 用户登录
     * @param loginName
     * @param password
     * @return
     */
    UserInfo login(String loginName,String password);
}
