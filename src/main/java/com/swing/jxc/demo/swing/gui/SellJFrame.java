package com.swing.jxc.demo.swing.gui;

import com.swing.jxc.demo.swing.common.PageVo;
import com.swing.jxc.demo.swing.model.Buy;
import com.swing.jxc.demo.swing.model.Sell;
import com.swing.jxc.demo.swing.service.BuyService;
import com.swing.jxc.demo.swing.service.SellService;
import com.swing.jxc.demo.swing.service.impl.BuyServiceImpl;
import com.swing.jxc.demo.swing.service.impl.SellServiceImpl;

import javax.swing.*;
import java.util.List;

/**
 * @description: 出库视图
 * @author: liujun 249489478@qq.com
 * @create: 2019-07-05 10:46
 */
public class SellJFrame {

    private JFrame jFrame;
//    private static String[] title = new String[]{"id","设备","库存"};
    private static String[] title = new String[]{"设备","数量"};
    public SellJFrame(){
    }

    public void initView(JPanel mainPanel){
        TablePanel table = new TablePanel();
        SellService sellService = new SellServiceImpl();
        PageVo pageVo = new PageVo();
        pageVo.setOffset(0);
        pageVo.setPageSize(2);
        List<Sell> sells = sellService.queryList(pageVo);
        System.out.println(sells.size());
        System.out.println(sells);
        table.initView(mainPanel,"出库管理",title,sells);
    }


    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
//        new TablePanel(jFrame,"进货管理",title);
    }
}
