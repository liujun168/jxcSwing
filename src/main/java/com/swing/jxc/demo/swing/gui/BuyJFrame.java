package com.swing.jxc.demo.swing.gui;

import com.swing.jxc.demo.swing.common.PageVo;
import com.swing.jxc.demo.swing.model.Buy;
import com.swing.jxc.demo.swing.service.BuyService;
import com.swing.jxc.demo.swing.service.impl.BuyServiceImpl;

import javax.swing.*;
import java.util.List;

/**
 * @description: 进货视图
 * @author: liujun 249489478@qq.com
 * @create: 2019-07-05 10:46
 */
public class BuyJFrame {

    private JFrame jFrame;

    private static String[] title = new String[]{"id","设备名称","数量"};
//    private static String[] title = new String[]{"设备名称","数量"};
    public BuyJFrame(){
    }

    public void initView(JPanel mainPanel){
        TablePanel table = new TablePanel();
        BuyService buyService = new BuyServiceImpl();
        PageVo pageVo = new PageVo();
        //不考虑分页
//        pageVo.setOffset(0);
//        pageVo.setPageSize(2);
        List<Buy> buys = buyService.queryList(pageVo);
        table.initView(mainPanel,"进货管理",title,buys,"buy");
    }


    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
//        new TablePanel(jFrame,"进货管理",title);
    }
}
