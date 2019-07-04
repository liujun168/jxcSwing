package com.jxc.swing.demo.gui;

import com.jxc.swing.demo.utils.MD5Util;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @description: Swing登录
 * @author: liujun 249489478@qq.com
 * @create: 2019-07-04 12:17
 */
public class SwingLoginExample extends JFrame{
    // 得到显示器屏幕的宽高
    public static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    // 定义窗体的宽高
    public static int windowsWedth = 600;
    public static int windowsHeight = 400;


    static JTextField userText = new JTextField(20);
    static JPasswordField passwordText = new JPasswordField(20);
    /**
     * 创建GUI视图
     */
    public static void createView(){
        // 创建 JFrame 实例
        JFrame frame = new JFrame("Login Example");

        // Setting the width and height of frame
        frame.setSize(windowsWedth, windowsHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* 创建面板，这个类似于 HTML 的 div 标签
         * 我们可以创建多个面板并在 JFrame 中指定位置
         * 面板中我们可以添加文本字段，按钮及其他组件。
         */
        JPanel panel = new JPanel();
        // 添加面板
        frame.add(panel);
        /*
         * 调用用户定义的方法并添加组件到面板
         */
        placeComponents(panel,frame);

        // 设置界面可见
        frame.setVisible(true);
        // 设置窗体位置和大小
        frame.setBounds((width - windowsWedth) / 2,
                (height - windowsHeight) / 2, windowsWedth, windowsHeight);
    }

    private static void placeComponents(JPanel panel,JFrame frame) {

        /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        panel.setLayout(null);

        // 创建 JLabel
        JLabel userLabel = new JLabel("User:");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
//        userLabel.setBounds(10,20,80,25);
        userLabel.setBounds(150,80,80,25);
        panel.add(userLabel);

        /*
         * 创建文本域用于用户输入
         */

//        userText.setBounds(100,20,165,25);
        userText.setBounds(240,80,165,25);
        panel.add(userText);

        // 输入密码的文本域
        JLabel passwordLabel = new JLabel("Password:");
//        passwordLabel.setBounds(10,50,80,25);
        passwordLabel.setBounds(150,110,80,25);
        panel.add(passwordLabel);

        /*
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
//        passwordText.setBounds(100,50,165,25);
        passwordText.setBounds(240,110,165,25);
        panel.add(passwordText);
        // 创建登录按钮
        JButton loginButton = new JButton("login");
//        loginButton.setBounds(10, 80, 80, 25);
        loginButton.setBounds(220, 180, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(Main.this, "You click button is " + name);
                getLoginData();
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(false);
                new MainSwing();
            }
        });
        panel.add(loginButton);
    }

    private static void getLoginData(){
        String userName = userText.getText();
        System.out.println(userName);
        String pwd = passwordText.getText();
        //密码MD5加密
        String md5 = MD5Util.md5(pwd);
        System.out.println(md5);
    }

    public static void main(String[] args) {
        createView();
    }
}
