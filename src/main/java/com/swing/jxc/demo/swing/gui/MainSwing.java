package com.swing.jxc.demo.swing.gui;

import com.swing.jxc.demo.swing.common.Constant;
import com.swing.jxc.demo.swing.common.Session;
import com.swing.jxc.demo.swing.model.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @description: 主界面
 * @author: liujun 249489478@qq.com
 * @create: 2019-07-04 12:40
 */
public class MainSwing extends JFrame {

    // 得到显示器屏幕的宽高
    public static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    JPanel mainPanel = new JPanel();

    JButton btn1 = new JButton("东");
    JButton btn2 = new JButton("南");
    JButton btn3 = new JButton("西");
    JButton btn4 = new JButton("北");
    JButton btn5 = new JButton("中");


    MainSwing() {
//        init();
        initView();
        this.setTitle("进销存管理");
        this.setResizable(true);
        if (width > 100) {
            width = width - 100;
        } else if (width > 50 && width <= 100) {
            width = width - 50;
        }
        if (height > 100) {
            height = height - 100;
        } else if (height > 50 && height <= 100) {
            height = height - 50;
        }
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    void init() {
        this.setLayout(new BorderLayout(10, 5)); //默认为0，0；水平间距10，垂直间距5
//        this.add(btn1, BorderLayout.EAST);
//        this.add(btn2, BorderLayout.SOUTH);
//        this.add(btn3, BorderLayout.WEST);
//        this.add(btn4, BorderLayout.NORTH);
//        this.add(btn5, BorderLayout.CENTER);
    }

    void initView() {
        this.setLayout(new BorderLayout(10, 5)); //默认为0，0；水平间距10，垂直间距5
        JPanel panel = new JPanel();
        this.add(panel, BorderLayout.WEST);
        menuPanel(panel);
//        this.add(btn1);
        this.add(mainPanel);
        int panelWidth = width-150;
        int panelHeight = height-150;
        Constant.panelWidth=panelWidth;
        Constant.panelHeight=panelHeight;
        initMainPanel(mainPanel);
    }

    /**
     * 左侧菜单
     * @param pNorth
     */
    JButton item4 = null;
    void menuPanel(JPanel pNorth) {
//        JPanel pNorth=new JPanel();
//        pNorth.setLayout(new GridLayout(8,1));
        pNorth.setPreferredSize(new Dimension(150, 200));
//        pNorth.setBorder(BorderFactory.createLineBorder(Color.blue));
        pNorth.setBackground(Color.WHITE);
//        JPanel pSouth=new JPanel();
//        JPanel subMenuContainer=new JPanel();
//
//        subMenuContainer.setLayout(new GridLayout(4,1));

        JButton item1 = new JButton("设备管理");              //设置按钮
        JButton item = new JButton("库存管理");
        JButton item2 = new JButton("进货管理");
        JButton item3 = new JButton("出货管理");
//        System.out.println("here:"+user.toString());
//        JButton item4=new JButton("医生信息管理");
//        JButton item5=new JButton("科室信息管理");
//        JButton item6=new JButton("价格管理");
//        JButton item7=new JButton("收费管理");
//        JButton item8=new JButton("系统设置");
        item.setPreferredSize(new Dimension(150, 30));
        item1.setPreferredSize(new Dimension(150, 30));   //优先设置按钮大小
        item2.setPreferredSize(new Dimension(150, 30));
        item3.setPreferredSize(new Dimension(150, 30));
//        item4.setPreferredSize(new Dimension(150, 47));
//        item5.setPreferredSize(new Dimension(150, 47));
//        item6.setPreferredSize(new Dimension(150, 47));
//        item7.setPreferredSize(new Dimension(150, 47));
//        item7.setPreferredSize(new Dimension(150, 47));
//        item8.setPreferredSize(new Dimension(150, 47));
        item1.setContentAreaFilled(true);
        item.setContentAreaFilled(false);
        item2.setContentAreaFilled(false);   //设置为透明
        item3.setContentAreaFilled(false);
//        item4.setContentAreaFilled(false);
//        item5.setContentAreaFilled(false);item6.setContentAreaFilled(false);
//        item7.setContentAreaFilled(false);item8.setContentAreaFilled(false);

        pNorth.add(item1);
        pNorth.add(item);
        pNorth.add(item2);
        pNorth.add(item3);
        UserInfo user = (UserInfo) Session.session.get(Session.sessionId);
        //TODO 用于测试
        if(user == null){
//            user=new UserInfo();
//            user.setIsAdmin(true);
            LoginSwingExample login = new LoginSwingExample();
            login.createView();
        }

        if(user.getIsAdmin()){
            item4 = new JButton("用户管理");
            item4.setPreferredSize(new Dimension(150, 30));
            item4.setContentAreaFilled(false);
            pNorth.add(item4);
            //用户管理
            item4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //按钮点击事件
                    item1.setContentAreaFilled(false);
                    item2.setContentAreaFilled(false);   //设置为透明
                    item3.setContentAreaFilled(false);
                    item.setContentAreaFilled(false);
                    if(item4 !=null){
                        item4.setContentAreaFilled(true);
                    }
                    UserJFrame userJFrame = new UserJFrame();
                    //重新绘图
                    mainPanel.removeAll();
                    mainPanel.repaint();
                    mainPanel.revalidate();
                    userJFrame.initView(mainPanel);
                }
            });
        }//添加按钮
//        pNorth.add(item4); pNorth.add(item5); pNorth.add(item6);
//        pNorth.add(item7);pNorth.add(item8);

        //设备管理
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //按钮点击事件
                item1.setContentAreaFilled(true);
                item2.setContentAreaFilled(false);   //设置为透明
                item3.setContentAreaFilled(false);
                item.setContentAreaFilled(false);
                if(item4 !=null){
                    item4.setContentAreaFilled(false);
                }

                MachineJFrame machineJFrame = new MachineJFrame();
                //重新绘图
                mainPanel.removeAll();
                mainPanel.repaint();
                mainPanel.revalidate();
                machineJFrame.initView(mainPanel);
            }
        });
        //库存管理
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //按钮点击事件
                item.setContentAreaFilled(true);
                item1.setContentAreaFilled(false);
                item2.setContentAreaFilled(false);   //设置为透明
                item3.setContentAreaFilled(false);
                if(item4 !=null){
                    item4.setContentAreaFilled(false);
                }

                StockJFrame stockFrame = new StockJFrame();
                //重新绘图
                mainPanel.removeAll();
                mainPanel.repaint();
                mainPanel.revalidate();
                stockFrame.initView(mainPanel);
            }
        });
        //进货管理
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //按钮点击事件
                item1.setContentAreaFilled(false);
                item2.setContentAreaFilled(true);   //设置为透明
                item3.setContentAreaFilled(false);
                item.setContentAreaFilled(false);
                if(item4 !=null){
                    item4.setContentAreaFilled(false);
                }
                BuyJFrame buyJFrame = new BuyJFrame();
                //重新绘图
                mainPanel.removeAll();
                mainPanel.repaint();
                mainPanel.revalidate();
                buyJFrame.initView(mainPanel);
            }
        });
        //出库
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //按钮点击事件
                item1.setContentAreaFilled(false);
                item2.setContentAreaFilled(false);   //设置为透明
                item3.setContentAreaFilled(true);
                item.setContentAreaFilled(false);
                if(item4 !=null){
                    item4.setContentAreaFilled(false);
                }
                SellJFrame sellJFrame = new SellJFrame();
                //重新绘图
                mainPanel.removeAll();
                mainPanel.repaint();
                mainPanel.revalidate();
                sellJFrame.initView(mainPanel);
            }
        });

    }

    /**
     * 初始化加载右侧内容
     * @param mainPanel
     */
    void initMainPanel(JPanel mainPanel) {
//        BuyJFrame jFrame = new BuyJFrame();
        MachineJFrame jFrame = new MachineJFrame();
        jFrame.initView(mainPanel);
//        mainPanel.add(item1);
//        initComponent(mainPanel,new String[]{"设备","库存"});
    }

    public static void main(String args[]) {
        new MainSwing();
    }
}