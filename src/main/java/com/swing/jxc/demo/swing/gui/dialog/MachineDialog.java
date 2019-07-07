package com.swing.jxc.demo.swing.gui.dialog;

import com.swing.jxc.demo.swing.common.Constant;
import com.swing.jxc.demo.swing.common.ResponseResult;
import com.swing.jxc.demo.swing.gui.MachineJFrame;
import com.swing.jxc.demo.swing.model.Machine;
import com.swing.jxc.demo.swing.service.MachineService;
import com.swing.jxc.demo.swing.service.impl.MachineServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/*
 * @功能：提供登陆UI并获取用户信息
 * @版本：20150804
 */
public class MachineDialog extends JDialog{
    private JTextField name ;
//    private JPasswordField password ;
    private JButton okButton ;
    private JButton cancelButton ;
//    private boolean isLogedIn = false;

    private String operationType=null;
    public MachineDialog(Frame parent, Map<String,Object> parameter,JPanel mainPanel){
        super(parent, true);
        if(parameter==null || parameter.size()<1){
            System.out.println("提供的参数不对");
            return;
        }
        String type = (String) parameter.get(Constant.operationType);
        operationType =type;
        //本UI包含2个panel
        JPanel inputPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        //构造inputPanel
//        inputPanel.setLayout(new GridLayout(2,2));
        setSize(400,400);
        inputPanel.add(new JLabel("设备名称:"));
        name = new JTextField();
        name.setColumns(10);
//        password = new JPasswordField();
//        password.setColumns(10);
        inputPanel.add(name);
//        inputPanel.add(new JLabel("Password:"));
//        inputPanel.add(password);
//        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
//        inputPanel.setBorder(BorderFactory.createEmptyBorder(100, 50, 100, 50));
        //构造buttonPanel
        okButton = new JButton("确定");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MachineService machineService=new MachineServiceImpl();
                if("add".equals(operationType)){
                    Machine machine = new Machine();
                    machine.setName(name.getText());
                    ResponseResult responseResult = machineService.addMachine(machine);
                    System.out.println(responseResult.toString());

                    MachineJFrame machineJFrame = new MachineJFrame();
                    //重新绘图
                    mainPanel.removeAll();
                    mainPanel.repaint();
                    mainPanel.revalidate();
                    machineJFrame.initView(mainPanel);
                }

                if("update".equals(operationType)){
                    Machine machine = (Machine) parameter.get(Constant.data);;
                    machine.setName(name.getText());
                    machineService.updateMachine(machine);
                }
//                isLogedIn = true;
                setVisible(false);
//                setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        });
        cancelButton = new JButton("取消");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                setVisible(false);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        setTitle("设备添加");
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
//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//    }
}