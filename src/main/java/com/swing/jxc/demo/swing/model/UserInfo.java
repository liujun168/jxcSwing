package com.swing.jxc.demo.swing.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户基础表
 * @Author liujun
 * @Date 2019/05/02 14:59
 */
@Setter
@Getter
@ToString
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 8199275447390785682L;

    private Long id;
    private String loginName;
    //用户名
    private String userName;
    //密码
    private String password;

    /**
     * 是否是管理员(1:是;0:否)
     */
    private Boolean isAdmin;
}
