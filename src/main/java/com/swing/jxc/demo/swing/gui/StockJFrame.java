package com.swing.jxc.demo.swing.gui;

import com.swing.jxc.demo.swing.common.PageVo;
import com.swing.jxc.demo.swing.model.Stock;
import com.swing.jxc.demo.swing.service.StockService;
import com.swing.jxc.demo.swing.service.impl.StockServiceImpl;

import javax.swing.*;
import java.util.List;

/**
 * @description: 库存视图
 * @author: liujun 249489478@qq.com
 * @create: 2019-07-05 10:46
 */
public class StockJFrame {

    private JFrame jFrame;
//    private static String[] title = new String[]{"id","设备","库存"};
    private static String[] title = new String[]{"id","设备","库存","出库数量","入库数量"};
    public StockJFrame(){
    }

    public void initView(JPanel mainPanel){
        TablePanel table = new TablePanel();
        StockService stockService = new StockServiceImpl();
        PageVo pageVo = new PageVo();
        //不考虑分页
//        pageVo.setOffset(0);
//        pageVo.setPageSize(2);
        List<Stock> stocks = stockService.queryList(pageVo);
        table.initView(mainPanel,"库存管理",title,stocks,"stock");
    }


    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
//        new TablePanel(jFrame,"进货管理",title);
    }
}
