package com.swing.jxc.demo.swing.service.impl;

import com.swing.jxc.demo.swing.common.PageVo;
import com.swing.jxc.demo.swing.common.ResponseResult;
import com.swing.jxc.demo.swing.jdbc.MyDataSource;
import com.swing.jxc.demo.swing.model.Machine;
import com.swing.jxc.demo.swing.service.MachineService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 设备
 * @author: liujun 249489478@qq.com
 * @create: 2019-07-05 13:45
 */
public class MachineServiceImpl implements MachineService {
    @Override
    public ResponseResult addMachine(Machine machine) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        //1.创建自定义连接池对象
        MyDataSource dataSource = new MyDataSource();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("insert into machine(name,delFlag) values (?,0) ");
            //2.从池子中获取连接
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            int index =1;
            if(machine !=null){
                pstmt.setString(index++, machine.getName());
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
    public ResponseResult updateMachine(Machine machine) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        //1.创建自定义连接池对象
        MyDataSource dataSource = new MyDataSource();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("update machine set name=? where id=? and delFlag=0 ");
            //2.从池子中获取连接
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            int index =1;
            if(machine !=null){
                pstmt.setString(index++, machine.getName());
                pstmt.setLong(index++, machine.getId());
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
    public ResponseResult deleteMachine(Long id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        //1.创建自定义连接池对象
        MyDataSource dataSource = new MyDataSource();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("update machine set delFlag=1 where id=? ");
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
    public List<Machine> queryList(PageVo pageVo) {
        return queryAll(pageVo);
    }

    @Override
    public List<Machine> queryAll(PageVo pageVo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        //1.创建自定义连接池对象
        MyDataSource dataSource = new MyDataSource();
        List<Machine> machines = new ArrayList<>();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT id,name,delFlag ");
            sql.append("from machine ");
            sql.append("WHERE 1=1 ");
            if(pageVo !=null && pageVo.getParameterMap()!=null &&pageVo.getParameterMap().size()>0){
                sql.append("AND name like concat(concat('%',?),'%') ");
            }
            sql.append("and delFlag =0 ");
            //暂时不考虑分页
//            sql.append("limit ?,?");

            //2.从池子中获取连接
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            int index=1;
            if(pageVo !=null && pageVo.getParameterMap()!=null &&pageVo.getParameterMap().size()>0){
                pstmt.setString(index++, (String) pageVo.getParameterMap().get("name"));
            }
            //暂时不考虑分页
//            pstmt.setInt(index++,pageVo.getOffset());
//            pstmt.setInt(index++,pageVo.getPageSize());
            ResultSet result = pstmt.executeQuery();
            while(result.next()){
                Machine machine = new Machine();
                machine.setId(result.getLong("id"));
                machine.setName(result.getString("name"));
                machine.setDelFlag(result.getBoolean("delFlag"));
                machines.add(machine);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            dataSource.backConnection(conn);
        }
        return machines;
    }
}
