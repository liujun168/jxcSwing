package com.swing.jxc.demo.swing.gui;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @description: 边框测试
 * @author: liujun 249489478@qq.com
 * @create: 2019-07-04 13:17
 */
public class JFrameBackground extends JFrame {
    public JFrameBackground() {
        this.setTitle("我的swing界面");
        this.setLayout(new FlowLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("分组框")); //设置面板边框，实现分组框的效果，此句代码为关键代码

        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.red));//设置面板边框颜色
        JButton button = new JButton("我的按钮");
        buttonPanel.add(button);
        this.setSize(300, 300);
        this.getContentPane().add(buttonPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new JFrameBackground();
    }
}
