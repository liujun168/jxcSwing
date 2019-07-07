package com.swing.jxc.demo.swing.gui;

import com.swing.jxc.demo.swing.common.Constant;
import com.swing.jxc.demo.swing.gui.dialog.BuyDialog;
import com.swing.jxc.demo.swing.gui.dialog.MachineDialog;
import com.swing.jxc.demo.swing.gui.dialog.SellDialog;
import com.swing.jxc.demo.swing.gui.dialog.UserDialog;
import com.swing.jxc.demo.swing.service.BuyService;
import com.swing.jxc.demo.swing.service.MachineService;
import com.swing.jxc.demo.swing.service.SellService;
import com.swing.jxc.demo.swing.service.UserService;
import com.swing.jxc.demo.swing.service.impl.BuyServiceImpl;
import com.swing.jxc.demo.swing.service.impl.MachineServiceImpl;
import com.swing.jxc.demo.swing.service.impl.SellServiceImpl;
import com.swing.jxc.demo.swing.service.impl.UserServiceImpl;
import com.swing.jxc.demo.swing.test.Student;
import com.swing.jxc.demo.swing.utils.TablePage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 表格分页
 * @author: liujun 249489478@qq.com
 * @create: 2019-07-05 10:01
 */
public class TablePanel extends JFrame implements ActionListener {
    private JScrollPane panel;
    private JButton next, previous, add, delete;
    private JLabel label1;
    private TablePage table;

    //操作类型用于判断调用哪个service
    private String type=null;

    private List data =null;
    private JPanel mainPanel=null;

    public void initView(JPanel mainPanel, String title, String[] columnNames, List list,String type) {
        this.mainPanel = mainPanel;
        mainPanel.setLayout(new GridLayout(2,0));
        JPanel topPanel = new JPanel(new GridLayout(2,0));
        JPanel buttomPanel = new JPanel();

        this.type = type;
        mainPanel.setBorder(BorderFactory.createTitledBorder(title));
//        mainPanel.setSize(Constant.panelWidth, Constant.panelHeight);
        data = list;
        table = new TablePage(columnNames,list,mainPanel);
        panel = new JScrollPane(table);

//        panel.setBorder(BorderFactory.createTitledBorder(title));
//        panel.setBounds(0, 0, 870, 119);
//        int height = Constant.panelHeight -150;
//        int width = Constant.panelWidth -150;
//        panel.setBounds(10, 10, Constant.panelWidth, Constant.panelHeight);
//        panel.setBackground(Color.red);
        previous = new JButton("上一页");
//        previous.setBounds(width, height, 75, 20);
        next = new JButton("下一页");
//        next.setBounds(width+105, height, 75, 20);
        add = new JButton("添加");
//        add.setBounds(width+200, height, 65, 20);
        delete = new JButton("删除");
//        delete.setBounds(width+420-150, height, 65, 20);

        previous.addActionListener(this);
        next.addActionListener(this);
        add.addActionListener(this);
        delete.addActionListener(this);

        label1 = new JLabel("总共" + table.totalRowCount + "记录|当前第"
                + table.currentPage + "页");
        label1.setBounds(10, 50, 130, 20);
//        this.setLayout(new BorderLayout(10, 5)); //默认为0，0；水平间距10，垂直间距5
//        mainPanel.add(panel);
//        mainPanel.add(label1,BorderLayout.SOUTH);

//        buttonPanel.setLayout(new FlowLayout());
//        mainPanel.add(buttonPanel);
//        mainPanel.add(buttonPanel,BorderLayout.SOUTH);
//        mainPanel.add(previous);
//        mainPanel.add(next);

        setLayout(null);//布局设置为null
        topPanel.add(panel);

        buttomPanel.add(label1);
        buttomPanel.add(previous);
        buttomPanel.add(next);

        if("user".equals(type) || "sell".equals(type) || "buy".equals(type)||"machine".equals(type)){
            buttomPanel.add(add);
            buttomPanel.add(delete);
        }
//        mainPanel.add(add);
//        mainPanel.add(delete);


//        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        jFrame.setLocationRelativeTo(null);
//        jFrame.setVisible(true);

        mainPanel.add(topPanel,BorderLayout.NORTH);
        mainPanel.add(buttomPanel,BorderLayout.SOUTH);

    }


//    private void initComponent(JFrame jFrame,String[] columnNames) {
//        jFrame.setSize(600, 600);
////        table = new TablePage(columnNames,null);
//        panel = new JScrollPane(table);
//        panel.setBounds(10, 10, 470, 119);
//        previous = new JButton("上一页");
//        previous.setBounds(150, 150, 75, 20);
//        next = new JButton("下一页");
//        next.setBounds(255, 150, 75, 20);
//        add = new JButton("添加");
//        add.setBounds(350, 150, 65, 20);
//        delete = new JButton("删除");
//        delete.setBounds(420, 150, 65, 20);
//        previous.addActionListener(this);
//        next.addActionListener(this);
//        add.addActionListener(this);
//        delete.addActionListener(this);
//        label1 = new JLabel("总共" + table.totalRowCount + "记录|当前第"
//                + table.currentPage + "页");
//        label1.setBounds(10, 150, 130, 20);
//        jFrame.getContentPane().add(panel);
//        jFrame.getContentPane().add(panel);
//        jFrame.getContentPane().add(previous);
//        jFrame.getContentPane().add(next);
//        jFrame.getContentPane().add(add);
//        jFrame.getContentPane().add(delete);
//        jFrame.getContentPane().add(label1);
//
//        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        jFrame.setLocationRelativeTo(null);
//        jFrame.setVisible(true);
//
//
////        this.getContentPane().setLayout(null);
////        this.getContentPane().add(panel);
////        this.getContentPane().add(previous);
////        this.getContentPane().add(next);
////        this.getContentPane().add(add);
////        this.getContentPane().add(delete);
////        this.getContentPane().add(label1);
//
////        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
////        this.setLocationRelativeTo(null);
////        this.setVisible(true);
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.equals(previous)) {
            int i = table.getPreviousPage();
            if (i == -1){
                return;
            }
        }
        if (button.equals(next)) {
            int i = table.getNextPage();
            if (i == -1){
                return;
            }
        }
        if (button.equals(delete)) {

            int i = table.getSelectedRow();
            if (i == -1){
                return;
            }
            Long id = (Long) table.getValueAt(i, 0);
//            System.out.println("this:"+id);
            if (id == null){
                return;
            }
            delete(id);
            Student s = null;
//            for (Student stu : Student.students) {
//                if (stu.getId().equals(id)){
//                    s = stu;
//                }
//            }
//            int index = Student.students.indexOf(s);
//            Student.students.remove(index);
            table.initTable(data);
            label1.setText("总共" + table.totalRowCount + "记录|当前第"
                    + table.currentPage + "页");
            return;
        }
        //添加点击事件
        if (button.equals(add)) {
            Map<String,Object> parameter = new HashMap<>();
            parameter.put(Constant.operationType,Constant.add);
            if("user".equals(type)){
                UserDialog user = new UserDialog(this,parameter,mainPanel);
                user.setVisible(true);
            }else if("sell".equals(type)){
                SellDialog sell = new SellDialog(this,parameter,mainPanel);
                sell.setVisible(true);
            }else if("buy".equals(type)){
                BuyDialog buy = new BuyDialog(this,parameter,mainPanel);
                buy.setVisible(true);
//        }else if("stock".equals(type)){
//            StockService stockService = new StockServiceImpl();
            }else if("machine".equals(type)){
                MachineDialog machine = new MachineDialog(this,parameter,mainPanel);
                machine.setVisible(true);
            }

//            MachineDialog sell = new MachineDialog(this,parameter);
//            sell.setVisible(true);

//            Integer id = 0;
//            for (Student stu : Student.students) {
//                if (stu.getId() > id){
//                    id = stu.getId();
//                }
//            }
//            Student student = new Student(id + 1, "wuming" + (id + 1), "男", 22);
//            Student.students.add(student);
            table.initTable(data);
            label1.setText("总共" + table.totalRowCount + "记录|当前第"
                    + table.currentPage + "页");
            return;
        }
        DefaultTableModel model = new DefaultTableModel(table.getPageData(),
                table.columnNames);
        table.setModel(model);
        label1.setText("总共" + table.totalRowCount + "记录|当前第"
                + table.currentPage + "页");
    }

    public void delete(Long id){
//        System.out.println(type);
        if("user".equals(type)){
            UserService userService = new UserServiceImpl();
            userService.deletedRecord(id);

            UserJFrame userJFrame = new UserJFrame();
            //重新绘图
            mainPanel.removeAll();
            mainPanel.repaint();
            mainPanel.revalidate();
            userJFrame.initView(mainPanel);
        }else if("sell".equals(type)){
            SellService sellService = new SellServiceImpl();
            sellService.deletedRecord(id);

            SellJFrame sellJFrame = new SellJFrame();
            //重新绘图
            mainPanel.removeAll();
            mainPanel.repaint();
            mainPanel.revalidate();
            sellJFrame.initView(mainPanel);
        }else if("buy".equals(type)){
            BuyService buyService = new BuyServiceImpl();
            buyService.deletedRecord(id);

            BuyJFrame buyJFrame = new BuyJFrame();
            //重新绘图
            mainPanel.removeAll();
            mainPanel.repaint();
            mainPanel.revalidate();
            buyJFrame.initView(mainPanel);
//        }else if("stock".equals(type)){
//            StockService stockService = new StockServiceImpl();
        }else if("machine".equals(type)){
            MachineService machineService =new MachineServiceImpl();
            machineService.deleteMachine(id);

            MachineJFrame machineJFrame = new MachineJFrame();
            //重新绘图
            mainPanel.removeAll();
            mainPanel.repaint();
            mainPanel.revalidate();
            machineJFrame.initView(mainPanel);
        }

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
//        JPanel mainPanel = new JPanel();
//        JFrame frame = new JFrame();
//        new TablePanel(frame,"分页",new String[]{"12","33"});
//        frame.add(mainPanel);
//
//        frame.setTitle("边界布局");
//        frame.setResizable(true);
//        frame.setSize(600, 600);
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        frame.setVisible(true);
    }
}
