package com.swing.jxc.demo.swing.gui.dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * @功能：提供登陆UI并获取用户信息
 * @版本：20150804
 */
public class SellDialogBk extends JDialog{
    private JTextField username ;
    private JPasswordField password ;
    private JButton okButton ;
    private JButton cancelButton ;
    private boolean isLogedIn = false;
    public SellDialogBk(Frame parent){
        super(parent, true);
        //本UI包含2个panel
        JPanel inputPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        //构造inputPanel
        inputPanel.setLayout(new GridLayout(2,2));
        inputPanel.add(new JLabel("Useername:"));
        username = new JTextField();
        username.setColumns(10);
        password = new JPasswordField();
        password.setColumns(10);
        inputPanel.add(username);
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(password);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        //构造buttonPanel
        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                isLogedIn = true;
                setVisible(false);
            }
        });
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                setVisible(false);
            }
        });
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        //构造主框架
        setLayout(new BorderLayout());
        getContentPane().add(inputPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        //设置窗体属性
        setTitle("PasswordChooser - www.jb51.net");
        setLocationRelativeTo(inputPanel);
        //setPreferredSize(new Dimension(300, 200));
        pack();
        //System.out.println(getPreferredSize());
    }
//    public void setUser(User u){
//        username.setText(u.getName());
//    }
//    public User getUser(){
//        return new User(username.getText(), password.getPassword());
//    }
//    public boolean isLogedIn(){
//        return isLogedIn;
//    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    }
}

//class User{
//    private String name;
//    private char[] password;
//    public User(String aName, char[] aPassword){
//        name = aName;
//        password = aPassword;
//    }
//    public String getName(){
//        return name;
//    }
//    public char[] getPassword(){
//        return password;
//    }
//    public void setName(String aName){
//        name = aName;
//    }
//    public void setPassword(char[] aPassword){
//        password = aPassword;
//    }
//}