package com.swing.jxc.demo.swing.gui;

import com.swing.jxc.demo.swing.common.PageVo;
import com.swing.jxc.demo.swing.model.Machine;
import com.swing.jxc.demo.swing.service.MachineService;
import com.swing.jxc.demo.swing.service.impl.MachineServiceImpl;

import javax.swing.*;
import java.util.List;

/**
 * @description: 进货视图
 * @author: liujun 249489478@qq.com
 * @create: 2019-07-05 10:46
 */
public class MachineJFrame {

    private JFrame jFrame;

//    private static String[] title = new String[]{"id","设备","库存"};
    private static String[] title = new String[]{"id","设备名称"};
//    private static String[] title = new String[]{"设备名称"};
    public MachineJFrame(){
    }

    public void initView(JPanel mainPanel){
        TablePanel table = new TablePanel();
        MachineService machineService =new MachineServiceImpl();
        PageVo pageVo = new PageVo();
        //不考虑分页
//        pageVo.setOffset(0);
//        pageVo.setPageSize(2);
        List<Machine> buys = machineService.queryList(pageVo);
        table.initView(mainPanel,"设备管理",title,buys,"machine");
    }


    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
//        new TablePanel(jFrame,"进货管理",title);
    }
}
