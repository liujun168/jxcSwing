package com.swing.jxc.demo.swing.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
 * @功能：使用JDialog构造登陆对话框，并获取登陆用户信息
 * @版本：20150804
 * @结构：LoginDemoStart[主类，提供初始UI]，PasswordChooser[提供登陆UI并获取用户信息]，User[保存用户信息]
 */
@SuppressWarnings("serial")
class LoginDemoStart extends JFrame{
    /*
     * @功能：提供初始UI，并调用PasswordChooser登陆界面
     * @版本：20150804
     */
    private JTextArea textArea;
    private PasswordChooser passwordChooser ;
    public LoginDemoStart(){
        //构造菜单栏
        JMenuBar mbar = new JMenuBar();
        setJMenuBar(mbar);
        JMenu fileMenu = new JMenu("File");
        JMenuItem connectItem = new JMenuItem("connect");
        connectItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                //if 1st time,construct a dialog
                passwordChooser = new PasswordChooser(LoginDemoStart.this);
                passwordChooser.setVisible(true);
                if(passwordChooser.isLogedIn())
                {
                    User u = passwordChooser.getUser();
                    textArea.append("Username = " + u.getName() + ", Password = " + new String(u.getPassword()) + "\n");
                }
            }
        });
        JMenuItem exitItem = new JMenuItem("exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.exit(0);
            }
        });
        mbar.add(fileMenu);
        fileMenu.add(connectItem);
        fileMenu.add(exitItem);
        //添加文本区域
        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        //设置窗体属性
        setTitle("LoginDemoStart - www.jb51.net");
        setSize(300,200);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        LoginDemoStart loginDemoStart = new LoginDemoStart();
        loginDemoStart.setVisible(true);
    }
}
