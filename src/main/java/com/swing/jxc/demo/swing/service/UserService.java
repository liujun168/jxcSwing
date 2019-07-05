package com.swing.jxc.demo.swing.service;

import com.swing.jxc.demo.swing.common.PageVo;
import com.swing.jxc.demo.swing.common.ResponseResult;
import com.swing.jxc.demo.swing.model.UserInfo;

import java.util.List;

public interface UserService {

    /**
     * 用户登录
     * @param loginName
     * @param password
     * @return
     */
    UserInfo login(String loginName,String password);

    /**
     * 添加记录
     * @param user
     * @return
     */
    ResponseResult addRecord(UserInfo user);

    /**
     * 修改记录
     * @param user
     * @return
     */
    ResponseResult updateRecord(UserInfo user);

    /**
     * 删除机记录
     * @param ids
     * @return
     */
    ResponseResult deletedRecord(List<Long> ids);

    /**
     * 查询数据
     * @param pageVo
     * @return
     */
    PageVo<UserInfo> queryList(PageVo pageVo);
}
