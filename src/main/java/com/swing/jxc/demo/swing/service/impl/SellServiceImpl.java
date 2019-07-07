package com.swing.jxc.demo.swing.service.impl;

import com.swing.jxc.demo.swing.common.PageVo;
import com.swing.jxc.demo.swing.common.ResponseResult;
import com.swing.jxc.demo.swing.jdbc.MyDataSource;
import com.swing.jxc.demo.swing.model.Sell;
import com.swing.jxc.demo.swing.service.SellService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 出库
 * @author: liujun 249489478@qq.com
 * @create: 2019-07-05 13:45
 */
public class SellServiceImpl implements SellService {
    @Override
    public ResponseResult addRecord(Sell sell) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        //1.创建自定义连接池对象
        MyDataSource dataSource = new MyDataSource();
        try {
            StringBuffer sql = new StringBuffer();
//            sql.append("insert into sell(contractNumber,customerName,contractDate,model,amount,actualSend,unitPrice,money ");
//            sql.append(",earnestMoney,tailMoney,payType,sendDate,receivables ");
//            sql.append(") values ");
//            sql.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

            sql.append("insert into sell(model,amount ");
            sql.append(") values ");
            sql.append("(?,?) ");
            //2.从池子中获取连接
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            int index =1;
            if(sell !=null){
                pstmt.setString(index++, sell.getModel());
                pstmt.setLong(index++, sell.getAmount());
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
    public ResponseResult updateRecord(Sell sell) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        //1.创建自定义连接池对象
        MyDataSource dataSource = new MyDataSource();
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("update sell set model=?,amount =?");
            sql.append("where id=?");
            //2.从池子中获取连接
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            int index =1;
            if(sell !=null){
                pstmt.setString(index++, sell.getModel());
                pstmt.setLong(index++, sell.getAmount());
                pstmt.setLong(index++, sell.getId());
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
            sql.append("delete from sell where id=? ");
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
    public List<Sell> queryList(PageVo pageVo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        //1.创建自定义连接池对象
        MyDataSource dataSource = new MyDataSource();
        List<Sell> sells = new ArrayList<>();
        Long total = 0L;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT s.id as id,contractNumber,customerName,contractDate,m.name as model,amount,actualSend,unitPrice,money ");
            sql.append(",earnestMoney,tailMoney,payType,sendDate,receivables ");
            sql.append("FROM sell s left join machine m on s.model = m.id ");
            sql.append("WHERE 1=1 ");
            if(pageVo !=null && pageVo.getParameterMap()!=null &&pageVo.getParameterMap().size()>0){
                sql.append("AND m.name = ? ");
            }
            //暂时不考虑分页
//            sql.append("limit ?,?");

            //2.从池子中获取连接
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            int index=1;
            if(pageVo !=null && pageVo.getParameterMap()!=null &&pageVo.getParameterMap().size()>0){
                pstmt.setString(index++, (String) pageVo.getParameterMap().get("model"));
            }
            //暂时不考虑分页
//            pstmt.setInt(index++,pageVo.getOffset());
//            pstmt.setInt(index++,pageVo.getPageSize());
            ResultSet result = pstmt.executeQuery();
            while(result.next()){
                Sell sell = new Sell();
                sell.setId(result.getLong("id"));
                sell.setModel(result.getString("model"));
                sell.setAmount(result.getLong("amount"));
                sells.add(sell);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            dataSource.backConnection(conn);
        }
        return sells;
    }
}
