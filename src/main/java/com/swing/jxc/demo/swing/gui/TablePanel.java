package com.swing.jxc.demo.swing.gui;

import com.swing.jxc.demo.swing.test.Student;
import com.swing.jxc.demo.swing.test.StudentTable;
import com.swing.jxc.demo.swing.utils.TablePage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

//    private JPanel mainPanel;

//    public TablePanel(){
//        super("");
//        initComponent();
//    }

    public TablePanel(JFrame jFrame,String title,String[] columnNames) {
        super(title);
//        initTableData();
        initComponent(jFrame,columnNames);
    }

    private void initComponent(JFrame jFrame,String[] columnNames) {
        jFrame.setSize(600, 600);
        table = new TablePage(columnNames);
        panel = new JScrollPane(table);
        panel.setBounds(10, 10, 470, 119);
        previous = new JButton("上一页");
        previous.setBounds(150, 150, 75, 20);
        next = new JButton("下一页");
        next.setBounds(255, 150, 75, 20);
        add = new JButton("添加");
        add.setBounds(350, 150, 65, 20);
        delete = new JButton("删除");
        delete.setBounds(420, 150, 65, 20);
        previous.addActionListener(this);
        next.addActionListener(this);
        add.addActionListener(this);
        delete.addActionListener(this);
        label1 = new JLabel("总共" + table.totalRowCount + "记录|当前第"
                + table.currentPage + "页");
        label1.setBounds(10, 150, 130, 20);
        jFrame.getContentPane().add(panel);
        jFrame.getContentPane().add(panel);
        jFrame.getContentPane().add(previous);
        jFrame.getContentPane().add(next);
        jFrame.getContentPane().add(add);
        jFrame.getContentPane().add(delete);
        jFrame.getContentPane().add(label1);

        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);


//        this.getContentPane().setLayout(null);
//        this.getContentPane().add(panel);
//        this.getContentPane().add(previous);
//        this.getContentPane().add(next);
//        this.getContentPane().add(add);
//        this.getContentPane().add(delete);
//        this.getContentPane().add(label1);

//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.setLocationRelativeTo(null);
//        this.setVisible(true);
    }

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
            Integer id = (Integer) table.getValueAt(i, 0);
            if (id == null){
                return;
            }
            Student s = null;
            for (Student stu : Student.students) {
                if (stu.getId().equals(id)){
                    s = stu;
                }
            }
            int index = Student.students.indexOf(s);
            Student.students.remove(index);
            table.initTable();
            label1.setText("总共" + table.totalRowCount + "记录|当前第"
                    + table.currentPage + "页");
            return;
        }
        //添加点击事件
        if (button.equals(add)) {
            Integer id = 0;
            for (Student stu : Student.students) {
                if (stu.getId() > id){
                    id = stu.getId();
                }
            }
            Student student = new Student(id + 1, "wuming" + (id + 1), "男", 22);
            Student.students.add(student);
            table.initTable();
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

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        JPanel mainPanel = new JPanel();
        JFrame frame = new JFrame();
        new TablePanel(frame,"分页",new String[]{"12","33"});
        frame.add(mainPanel);

        frame.setTitle("边界布局");
        frame.setResizable(true);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
