package com.swing.jxc.demo.swing.service.impl;

import com.swing.jxc.demo.swing.common.PageVo;
import com.swing.jxc.demo.swing.common.ResponseResult;
import com.swing.jxc.demo.swing.jdbc.MyDataSource;
import com.swing.jxc.demo.swing.model.UserInfo;
import com.swing.jxc.demo.swing.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Override
    public UserInfo login(String loginName, String password) {
        UserInfo userInfo = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        //1.创建自定义连接池对象
        MyDataSource dataSource = new MyDataSource();
        try {
            //2.从池子中获取连接
            String sql = "select * from userinfo where loginName=? and password=?";
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,loginName);
            pstmt.setString(2,password);
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("loginName");
                String userName = resultSet.getString("userName");
                String pwd = resultSet.getString("password");
                boolean isAdmin = resultSet.getBoolean("isAdmin");
                userInfo = new UserInfo();
                userInfo.setId(id);
                userInfo.setLoginName(name);
                userInfo.setUserName(userName);
                userInfo.setPassword(pwd);
                userInfo.setIsAdmin(isAdmin);
                return userInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            dataSource.backConnection(conn);
        }
        return userInfo;
    }

    @Override
    public ResponseResult addRecord(UserInfo user) {
        return null;
    }

    @Override
    public ResponseResult updateRecord(UserInfo user) {
        return null;
    }

    @Override
    public ResponseResult deletedRecord(List<Long> ids) {
        return null;
    }

    @Override
    public PageVo<UserInfo> queryList(PageVo pageVo) {
        return null;
    }
}
