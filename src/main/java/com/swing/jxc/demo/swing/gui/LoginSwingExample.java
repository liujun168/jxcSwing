package com.swing.jxc.demo.swing.gui;


import com.swing.jxc.demo.swing.common.Session;
import com.swing.jxc.demo.swing.model.UserInfo;
import com.swing.jxc.demo.swing.service.UserService;
import com.swing.jxc.demo.swing.service.impl.UserServiceImpl;
import com.swing.jxc.demo.swing.utils.MD5Util;
import org.springframework.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * @description: Swing登录
 * @author: liujun 249489478@qq.com
 * @create: 2019-07-04 12:17
 */
public class LoginSwingExample extends JFrame {
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
    public void createView() {
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
        placeComponents(panel, frame);

        // 设置界面可见
        frame.setVisible(true);
        // 设置窗体位置和大小
        frame.setBounds((width - windowsWedth) / 2,
                (height - windowsHeight) / 2, windowsWedth, windowsHeight);
    }

    private void placeComponents(JPanel panel, JFrame frame) {

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
        userLabel.setBounds(150, 80, 80, 25);
        panel.add(userLabel);

        /*
         * 创建文本域用于用户输入
         */

//        userText.setBounds(100,20,165,25);
        userText.setBounds(240, 80, 165, 25);
        panel.add(userText);

        // 输入密码的文本域
        JLabel passwordLabel = new JLabel("Password:");
//        passwordLabel.setBounds(10,50,80,25);
        passwordLabel.setBounds(150, 110, 80, 25);
        panel.add(passwordLabel);

        /*
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
//        passwordText.setBounds(100,50,165,25);
        passwordText.setBounds(240, 110, 165, 25);
        panel.add(passwordText);
        // 创建登录按钮
        JButton loginButton = new JButton("登录");
        JButton exitButton = new JButton("取消");
//        loginButton.setBounds(10, 80, 80, 25);
        loginButton.setBounds(220, 180, 80, 25);
        exitButton.setBounds(310, 180, 80, 25);

        //注冊回车键事件
        loginButton.registerKeyboardAction(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               //输入值为空
               if(!checkValue()){
                   return;
               }
               Boolean isLogin = getLoginData();
               if(isLogin){
                   frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                   frame.setVisible(false);
                   new MainSwing();
               }else{
                   JOptionPane.showMessageDialog(LoginSwingExample.this, "用户名或密码错误!请重新登录");
               }
           }
       } , KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //输入值为空
                if(!checkValue()){
                    return;
                }
//                JOptionPane.showMessageDialog(Main.this, "You click button is " + name);
                Boolean isLogin = getLoginData();
                if(isLogin){
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setVisible(false);
                    new MainSwing();
                }else{
                    JOptionPane.showMessageDialog(LoginSwingExample.this, "用户名或密码错误!请重新登录");
//                    JOptionPane.showMessageDialog(null, "警告","提示",JOptionPane.WARNING_MESSAGE);
//                    JOptionPane.showMessageDialog(null, "警告","提示",JOptionPane.YES_NO_CANCEL_OPTION);
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(loginButton);
        panel.add(exitButton);
    }

    private static Boolean getLoginData() {
        String userName = userText.getText();
        System.out.println(userName);
        String pwd = passwordText.getText();
        //密码MD5加密
        String md5 = MD5Util.md5(pwd);
        System.out.println(md5);
        UserService userService = new UserServiceImpl();

        UserInfo user = userService.login(userName, md5);
        Session.session.put(Session.sessionId,user);
        if(user != null){
            return true;
        }
        return false;
    }

    /**
     * 校验输入值是否为空
     * @return
     */
    public boolean checkValue(){
        String userName = userText.getText();
        if(StringUtils.isEmpty(userName)){
            JOptionPane.showMessageDialog(LoginSwingExample.this, "用户名错误!请重新登录");
            return false;
        }
        String pwd = passwordText.getText();
        if(StringUtils.isEmpty(pwd)){
            JOptionPane.showMessageDialog(LoginSwingExample.this, "密码错误!请重新登录");
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        LoginSwingExample login = new LoginSwingExample();
        login.createView();
    }
}
