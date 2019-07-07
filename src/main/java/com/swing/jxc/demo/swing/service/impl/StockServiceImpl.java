package com.swing.jxc.demo.swing.service.impl;

import com.swing.jxc.demo.swing.common.PageVo;
import com.swing.jxc.demo.swing.jdbc.MyDataSource;
import com.swing.jxc.demo.swing.model.Stock;
import com.swing.jxc.demo.swing.service.StockService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 库存
 * @author: liujun 249489478@qq.com
 * @create: 2019-07-05 13:46
 */
public class StockServiceImpl implements StockService {
    @Override
    public List<Stock> queryList(PageVo pageVo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        //1.创建自定义连接池对象
        MyDataSource dataSource = new MyDataSource();
        List<Stock> stocks = new ArrayList<>();
        Long total = 0L;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT m.id as id,m.name as modelName,IFNULL(sum(s.amount),0) as sellNum,IFNULL(sum(b.amount),0) as buyNum, ");
            sql.append("IFNULL(sum(b.amount),0)-IFNULL(sum(s.amount),0) as stockNum ");
            sql.append("from machine m ");
            sql.append("left join sell s on m.id=s.model ");
            sql.append("left join buy b on m.id=b.model ");
            sql.append("WHERE 1=1 ");
            if(pageVo !=null && pageVo.getParameterMap()!=null &&pageVo.getParameterMap().size()>0){
                sql.append("AND m.name = ? ");
            }
            sql.append(" and m.delFlag =0 ");
            sql.append(" group by m.id ");
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
                Stock stock = new Stock();
                stock.setId(result.getLong("id"));
                stock.setModelName(result.getString("modelName"));
                stock.setSellNum(result.getLong("sellNum"));
                stock.setBuyNum(result.getLong("buyNum"));
                stock.setStockNum(result.getLong("stockNum"));
                stocks.add(stock);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            dataSource.backConnection(conn);
        }
        return stocks;
    }
}
