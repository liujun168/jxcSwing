package com.swing.jxc.demo.swing.gui;

import com.swing.jxc.demo.swing.common.PageVo;
import com.swing.jxc.demo.swing.model.UserInfo;
import com.swing.jxc.demo.swing.service.UserService;
import com.swing.jxc.demo.swing.service.impl.UserServiceImpl;

import javax.swing.*;
import java.util.List;

/**
 * @description: 用户视图
 * @author: liujun 249489478@qq.com
 * @create: 2019-07-05 10:46
 */
public class UserJFrame {

    private JFrame jFrame;
//    private static String[] title = new String[]{"id","设备","库存"};
    private static String[] title = new String[]{"ID","登录名称","用户名"};
//    private static String[] title = new String[]{"登录名称","用户名"};
    public UserJFrame(){
    }

    public void initView(JPanel mainPanel){
        TablePanel table = new TablePanel();
        UserService stockService = new UserServiceImpl();
        PageVo pageVo = new PageVo();
        //不考虑分页
//        pageVo.setOffset(0);
//        pageVo.setPageSize(2);
        List<UserInfo> users = stockService.queryList(pageVo);
        table.initView(mainPanel,"用户管理",title,users,"user");
    }


    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
//        new TablePanel(jFrame,"进货管理",title);
    }
}
