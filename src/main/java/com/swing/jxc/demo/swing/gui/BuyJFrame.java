package com.swing.jxc.demo.swing.gui;

import javax.swing.*;
import java.awt.*;

/**
 * @description: 进货视图
 * @author: liujun 249489478@qq.com
 * @create: 2019-07-05 10:46
 */
public class BuyJFrame {

    private JPanel mainPanel;
    private JFrame jFrame;

    private static String[] title = new String[]{"设备","库存"};
    public BuyJFrame(){
    }

//    public JFrame getjFrame(JFrame jFrame) {
//        return jFrame;
//    }

    public BuyJFrame(JFrame jFrame){
        this.jFrame = jFrame;
    }
    public void initView(){
        TablePanel table = new TablePanel(jFrame,"进货管理",new String[]{});
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        new TablePanel(jFrame,"进货管理",title);
    }
}
