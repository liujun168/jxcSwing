package com.swing.jxc.demo.swing.gui;

import com.swing.jxc.demo.swing.common.Session;
import com.swing.jxc.demo.swing.model.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @description: 主界面
 * @author: liujun 249489478@qq.com
 * @create: 2019-07-04 12:40
 */
public class MainSwing extends JFrame {

    // 得到显示器屏幕的宽高
    public static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    JButton btn1 = new JButton("东");
    JButton btn2 = new JButton("南");
    JButton btn3 = new JButton("西");
    JButton btn4 = new JButton("北");
    JButton btn5 = new JButton("中");


    MainSwing() {
//        init();
        initView();
        this.setTitle("边界布局");
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
        JPanel mainPanel = new JPanel();
        this.add(mainPanel);
        mainPanel(mainPanel);
    }

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

        JButton item1 = new JButton("机器管理");              //设置按钮
        JButton item2 = new JButton("进货管理");
        JButton item3 = new JButton("出货管理");
//        System.out.println("here:"+user.toString());
//        JButton item4=new JButton("医生信息管理");
//        JButton item5=new JButton("科室信息管理");
//        JButton item6=new JButton("价格管理");
//        JButton item7=new JButton("收费管理");
//        JButton item8=new JButton("系统设置");
        item1.setPreferredSize(new Dimension(150, 30));   //优先设置按钮大小
        item2.setPreferredSize(new Dimension(150, 30));
        item3.setPreferredSize(new Dimension(150, 30));
//        item4.setPreferredSize(new Dimension(150, 47));
//        item5.setPreferredSize(new Dimension(150, 47));
//        item6.setPreferredSize(new Dimension(150, 47));
//        item7.setPreferredSize(new Dimension(150, 47));
//        item7.setPreferredSize(new Dimension(150, 47));
//        item8.setPreferredSize(new Dimension(150, 47));
        item1.setContentAreaFilled(false);
        item2.setContentAreaFilled(false);   //设置为透明
        item3.setContentAreaFilled(false);
//        item4.setContentAreaFilled(false);
//        item5.setContentAreaFilled(false);item6.setContentAreaFilled(false);
//        item7.setContentAreaFilled(false);item8.setContentAreaFilled(false);

        pNorth.add(item1);
        pNorth.add(item2);
        pNorth.add(item3);
        UserInfo user = (UserInfo) Session.session.get(Session.sessionId);
        if(user.getIsAdmin()){
            JButton item4 = new JButton("用户管理");
            item4.setPreferredSize(new Dimension(150, 30));
            item4.setContentAreaFilled(false);
            pNorth.add(item4);
        }//添加按钮
//        pNorth.add(item4); pNorth.add(item5); pNorth.add(item6);
//        pNorth.add(item7);pNorth.add(item8);

    }

    void mainPanel(JPanel mainPanel) {

    }

    public static void main(String args[]) {
        new MainSwing();
    }
}

class MyMenu {
    JFrame frame = new JFrame("右键菜单");
    JMenuItem item1 = new JMenuItem("子菜单1", new ImageIcon("close.gif"));
    JMenuItem item2 = new JMenuItem("子菜单2");
    JMenuItem item3 = new JMenuItem("子菜单3");
    JPopupMenu menu = new JPopupMenu();
    JMenu m = new JMenu();
    JPanel panel = new JPanel();

    public MyMenu() {
        menu.add(new JMenuItem("选择"));
        menu.add(new JMenuItem("退出"));
        m.add(item1);
        m.add(item2);
        menu.add(m);
        menu.add(item3);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    menu.show(panel, e.getX(), e.getY());
                }
            }
        });

        panel.add(menu);
        frame.add(panel);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

}
