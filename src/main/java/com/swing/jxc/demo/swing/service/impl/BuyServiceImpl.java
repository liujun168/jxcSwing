package com.swing.jxc.demo.swing.service.impl;

import com.swing.jxc.demo.swing.common.PageVo;
import com.swing.jxc.demo.swing.common.ResponseResult;
import com.swing.jxc.demo.swing.jdbc.MyDataSource;
import com.swing.jxc.demo.swing.model.Buy;
import com.swing.jxc.demo.swing.model.UserInfo;
import com.swing.jxc.demo.swing.service.BuyService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: 进货service实现类
 * @author: liujun 249489478@qq.com
 * @create: 2019-07-05 10:18
 */
public class BuyServiceImpl implements BuyService {
    @Override
    public ResponseResult addRecord(Buy buy) {

        return null;
    }

    @Override
    public ResponseResult updateRecord(Buy buy) {
        return null;
    }

    @Override
    public ResponseResult deletedRecord(List<Long> ids) {
        return null;
    }

    @Override
    public List<Buy> queryList(PageVo pageVo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        //1.创建自定义连接池对象
        MyDataSource dataSource = new MyDataSource();
        List<Buy> buys = new ArrayList<>();
        Long total = 0L;
        try {
            //统计总数
            StringBuffer countSql = new StringBuffer();
            countSql.append("SELECT count(1) ");
            countSql.append("FROM buy b left join machine m on b.model = m.id ");
            countSql.append("where 1=1 ");
            if(pageVo !=null && pageVo.getParameterMap()!=null &&pageVo.getParameterMap().size()>0){
                countSql.append("AND m.name = ? ");
            }
            //2.从池子中获取连接
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(countSql.toString());
            if(pageVo !=null && pageVo.getParameterMap()!=null &&pageVo.getParameterMap().size()>0){
                pstmt.setString(1, (String) pageVo.getParameterMap().get("model"));
            }
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                total = resultSet.getLong(1);
            }

            StringBuffer sql = new StringBuffer();
            sql.append("SELECT b.id as id,contractNumber,customerName,contractDate,m.name as model,amount,actualSend,unitPrice,money ");
            sql.append(",earnestMoney,tailMoney,payType,sendDate,receivables ");
            sql.append("FROM buy b left join machine m on b.model = m.id ");
            sql.append("WHERE 1=1 ");
            if(pageVo !=null && pageVo.getParameterMap()!=null &&pageVo.getParameterMap().size()>0){
                countSql.append("AND m.name = ? ");
            }
            sql.append("limit ?,?");

            //2.从池子中获取连接
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            if(pageVo !=null && pageVo.getParameterMap()!=null &&pageVo.getParameterMap().size()>0){
                pstmt.setString(1, (String) pageVo.getParameterMap().get("model"));
            }
            pstmt.setInt(2,pageVo.getOffset());
            pstmt.setInt(3,pageVo.getPageSize());
            ResultSet result = pstmt.executeQuery();
            while(result.next()){
                Buy buy = new Buy();
                buy.setId(result.getLong("id"));
                buy.setModel(result.getString("model"));
                buy.setAmount(result.getLong("amount"));
                buys.add(buy);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            dataSource.backConnection(conn);
        }

        return buys;
    }
}
