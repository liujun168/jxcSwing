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
import java.util.ArrayList;
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
        Connection conn = null;
        PreparedStatement pstmt = null;
        //1.创建自定义连接池对象
        MyDataSource dataSource = new MyDataSource();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("insert into userinfo(loginName,userName,password,isAdmin) ");
            sql.append(" values ");
            sql.append("(?,?,?,?) ");
            //2.从池子中获取连接
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            int index =1;
            if(user !=null){
                pstmt.setString(index++, user.getLoginName());
                pstmt.setString(index++, user.getUserName());
                pstmt.setString(index++, user.getPassword());
                pstmt.setLong(index++, 0);
            }
            int num = pstmt.executeUpdate();
            if(num >0){
                return ResponseResult.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            dataSource.backConnection(conn);
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult updateRecord(UserInfo user) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        //1.创建自定义连接池对象
        MyDataSource dataSource = new MyDataSource();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append(" update userinfo set loginName=?,userName=?,password=?,isAdmin=? ");
            sql.append(" where id=? ");
            //2.从池子中获取连接
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            int index =1;
            if(user !=null){
                pstmt.setString(index++, user.getLoginName());
                pstmt.setString(index++, user.getUserName());
                pstmt.setString(index++, user.getPassword());
                pstmt.setBoolean(index++, user.getIsAdmin());
                pstmt.setLong(index++, user.getId());
            }
            int num = pstmt.executeUpdate();
            if(num >0){
                return ResponseResult.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            dataSource.backConnection(conn);
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult deletedRecord(Long id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        //1.创建自定义连接池对象
        MyDataSource dataSource = new MyDataSource();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("delete from userinfo where id=? ");
            //2.从池子中获取连接
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            if(id !=null){
                pstmt.setLong(1, id);
            }
            int num = pstmt.executeUpdate();
            if(num >0){
                return ResponseResult.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            dataSource.backConnection(conn);
        }
        return ResponseResult.error();
    }

    @Override
    public List<UserInfo> queryList(PageVo pageVo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        //1.创建自定义连接池对象
        MyDataSource dataSource = new MyDataSource();
        List<UserInfo> users = new ArrayList<>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT id,loginName,userName,isAdmin ");
            sql.append("from userinfo ");
            sql.append("WHERE 1=1 ");
            if(pageVo !=null && pageVo.getParameterMap()!=null &&pageVo.getParameterMap().size()>0){
                sql.append("AND userName like concat(concat('%',?),'%') ");
            }
            //暂时不考虑分页
//            sql.append("limit ?,?");

            //2.从池子中获取连接
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            int index=1;
            if(pageVo !=null && pageVo.getParameterMap()!=null &&pageVo.getParameterMap().size()>0){
                pstmt.setString(index++, (String) pageVo.getParameterMap().get("userName"));
            }
            //暂时不考虑分页
//            pstmt.setInt(index++,pageVo.getOffset());
//            pstmt.setInt(index++,pageVo.getPageSize());
            ResultSet result = pstmt.executeQuery();
            while(result.next()){
                UserInfo user = new UserInfo();
                user.setId(result.getLong("id"));
                user.setLoginName(result.getString("loginName"));
                user.setUserName(result.getString("userName"));
                user.setIsAdmin(result.getBoolean("isAdmin"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            dataSource.backConnection(conn);
        }
        return users;
    }
}
