package com.swing.jxc.demo.swing.utils;

/**
 * @description: table分页测试
 * @author: liujun 249489478@qq.com
 * @create: 2019-07-05 09:48
 */

import com.swing.jxc.demo.swing.model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

@SuppressWarnings("serial")
public class TablePage<T> extends JTable {
    // JTable表分页信息相关变量
    public int currentPage = 1;
    public int pageCount = 5;
    public int totalPage = 0;
    public int totalRowCount = 0;
    public int column = 0;
    public int restCount;
    public int columnNum=1;
    public Object[][] resultData;
    // JTable表信息相关变量
//    public List<Student> students = Student.students;
//    public List<T> datas = new ArrayList<>();
//    public String[] columnNames = { "ID", "Name", "Sex", "Age" };
    public String[] columnNames = null;
    public DefaultTableModel model = null;
    private JPanel mainPanel;

    public TablePage(String[] columnNames,List<T> list,JPanel mainPanel) {
        this.columnNames = columnNames;
        this.columnNum = columnNames.length;
        this.mainPanel = mainPanel;
        initTable(list);
    }

    /**
     * 获取下一页
     */
    public int getNextPage() {
        if (this.currentPage != this.totalPage) {
            return ++currentPage;
        }
        return -1;
    }

    /**
     * 获取上一页
     */
    public int getPreviousPage() {
        if (this.currentPage != 1) {
            return --currentPage;
        }
        return -1;
    }

    /**
     * 获取最后一页
     */
    public int getLastPage() {
        return this.totalPage;
    }

    /**
     * 获取第一页
     */
    public int getFirstPage() {
        return 1;
    }

    /**
     * 获取总页数
     */
    public int getTotolPage() {
        return this.totalPage;
    }

    /**
     * 获取当前页
     */
    public int getCurrentPage() {
        return this.currentPage;
    }

    /**
     * 获得原始数据集
     *
     * @param values
     * @return
     */

//    public Object[][] getData(List<Student> students) {
    public Object[][] getData(List<T> values) {
        if (values.size() > 0) {
            Object[][] data = new Object[values.size()][columnNum];
            for (int i = 0; i < values.size(); i++) {
                T t = values.get(i);
                //进货
                if(t instanceof Stock){
                    Stock stock = (Stock) t;
                    Object[] a = {stock.getId(),stock.getModelName(),stock.getStockNum(),stock.getSellNum(),stock.getBuyNum()};// 把List**的数据赋给Object数组
//                    Object[] a = {stock.getModelName(),stock.getStockNum(),stock.getSellNum(),stock.getBuyNum()};// 把List**的数据赋给Object数组
                    data[i] = a;// 把数组的值赋给二维数组的一行
                }
                //进货
                if(t instanceof  Buy){
                    Buy buy = (Buy) t;
                    Object[] a = { buy.getId(),buy.getModel(),buy.getAmount()};// 把List**的数据赋给Object数组
//                    Object[] a = {buy.getModel(),buy.getAmount()};// 把List**的数据赋给Object数组
                    data[i] = a;// 把数组的值赋给二维数组的一行
                }
                //用户
                if(t instanceof UserInfo){
                    UserInfo user = (UserInfo) t;
                    Object[] a = { user.getId(),user.getLoginName(),user.getUserName()};// 把List**的数据赋给Object数组
//                    Object[] a = {user.getLoginName(),user.getUserName()};// 把List**的数据赋给Object数组
                    data[i] = a;// 把数组的值赋给二维数组的一行
                }
                //出库
                if(t instanceof Sell){
                    Sell sell = (Sell) t;
                    Object[] a = { sell.getId(),sell.getModel(),sell.getAmount()};// 把List**的数据赋给Object数组
//                    Object[] a = { sell.getModel(),sell.getAmount()};// 把List**的数据赋给Object数组
                    data[i] = a;// 把数组的值赋给二维数组的一行
                }
                //设备
                if(t instanceof Machine){
                    Machine machine = (Machine) t;
                    Object[] a = { machine.getId(),machine.getName()};// 把List**的数据赋给Object数组
//                    Object[] a = { machine.getName()};// 把List**的数据赋给Object数组
                    data[i] = a;// 把数组的值赋给二维数组的一行
                }
            }
            return data;
        }
//        if (students.size() > 0) {
//            Object[][] data = new Object[students.size()][4];
//            for (int i = 0; i < students.size(); i++) {
//                Student s = students.get(i);
//                Object[] a = { s.getId(), s.getName(), s.getSex(), s.getAge() };// 把List**的数据赋给Object数组
//                data[i] = a;// 把数组的值赋给二维数组的一行
//            }
//            return data;
//        }
        return null;
    }

    public void initResultData(Object[][] data) {
        if (data != null) {
            resultData = data;// 总的结果集
            column = data[0].length;// 表的列数
            totalRowCount = data.length;// 表的长度
            totalPage = totalRowCount % pageCount == 0 ? totalRowCount
                    / pageCount : totalRowCount / pageCount + 1;// 结果集的总页数
            restCount = totalRowCount % pageCount == 0 ? 5 : totalRowCount
                    % pageCount;// 最后一页的数据数
        }
    }

    /**
     * 获取分页数据
     *
     * @return
     */
    public Object[][] getPageData() {
        Object[][] currentPageData = new Object[pageCount][column];// 构造每页数据集
        if (this.getCurrentPage() < this.totalPage) {// 如果当前页数小于总页数，那么每页数目应该是规定的数pageCount
            for (int i = pageCount * (this.getCurrentPage() - 1); i < pageCount
                    * (this.getCurrentPage() - 1) + pageCount; i++) {
                for (int j = 0; j < column; j++) {
                    // 把结果集中对应每页的每一行数据全部赋值给当前页的每一行的每一列
                    currentPageData[i % pageCount][j] = resultData[i][j];
                }
            }
        } else {
            // 在动态改变数据结果集的时候，如果当前页没有数据了，则回到前一页（一般针对最后一页而言）
            if (pageCount * (this.getCurrentPage() - 1) >= totalRowCount)
                this.currentPage--;
            for (int i = pageCount * (this.getCurrentPage() - 1); i < pageCount
                    * (this.getCurrentPage() - 1) + restCount; i++) {
                for (int j = 0; j < column; j++) {
                    currentPageData[i % pageCount][j] = resultData[i][j];
                }
            }
        }
        return currentPageData;
    }

    /**
     * 初始化表格数据
     */
    public void initTable(List<T> datas) {
        Object[][] data = getData(datas);
        if (data != null) {
            initResultData(data);
            model = new DefaultTableModel(getPageData(), columnNames){
                public boolean isCellEditable(int row, int column){
                    return false;
//                    return getValueAt(row, column).getClass() == Boolean.class;
                }
//                @Override
//                public Class getColumnClass(int columnIndex) {
//                    return getValueAt(0, columnIndex).getClass();
//                }
            };
        } else {
            // 如果结果集中没有数据，那么就用空来代替数据集中的每一行
            Object[][] nothing = { {}, {}, {}, {}, {} };
            model = new DefaultTableModel(nothing, columnNames);
            totalRowCount = 0;
        }
        this.setModel(model);

//        int width  = Constant.panelWidth/columnNames.length;
//        for (int i = 0; i < columnNames.length; i++) {
//            // 设置表格各栏各行的尺寸
//            this.getColumnModel().getColumn(i).setPreferredWidth(700);
//        }
//        this.setRowHeight(30);
//        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
//        r.setHorizontalAlignment(JLabel.LEFT);
        setDefaultRenderer(Object.class, r);
//        this.setFillsViewportHeight(true);
        this.setBounds(0, 0, 870, 119);
        //===========设置表的点击方式为只可单选===============
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        mainPanel.add(this, BorderLayout.CENTER);

        // 使用普通的中间容器添加表格时，表头 和 内容 需要分开添加
//        mainPanel.add(this.getTableHeader(), BorderLayout.NORTH);
//        mainPanel.add(this, BorderLayout.CENTER);

        //隐藏第一列
        TableColumn tc=this.getColumnModel().getColumn(0);
        tc.setMaxWidth(0);
        tc.setMinWidth(0);
        tc.setPreferredWidth(0);
//        tc.setResizable(false);
//        this.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0); //设置表的标题的宽度也为0,这个很重要
//        this.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);


        this.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                Object o = e.getSource();
//                if(o instanceof JTable){
//                    JTable t = (JTable) o;
//                    t.getSelectionModel();
//                    System.out.println(t.getSelectedRow());
//                    System.out.println("===================");
//                    DefaultTableModel tb = (DefaultTableModel) t.getModel();
//                    //获取选中的单元格值
//                    System.out.println(tb.getValueAt(t.getSelectedRow(), t.getSelectedColumn()));
//                    //修改选中单元格的值
//                    if(tb.isCellEditable(t.getSelectedRow(), t.getSelectedColumn())){
//                        tb.setValueAt("1", t.getSelectedRow(), t.getSelectedColumn());
//                    }
//                }
//            }
            @Override
            public void mouseClicked(MouseEvent e) {
//                System.out.println("click");
//                createPopupMenu();
//                super.mouseClicked(e);
//                if(this.getValueAt(model.getSelectedRow(),0)!=null){
//
//                }
            }
        });
    }

//    JPopupMenu m_popupMenu=null;
//    private void createPopupMenu() {
//        m_popupMenu = new JPopupMenu();
//
//        JMenuItem delMenItem = new JMenuItem();
//        delMenItem.setText("  删除  ");
//        delMenItem.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                //该操作需要做的事
//            }
//        });
//        m_popupMenu.add(delMenItem);
//    }
//
//    //鼠标右键点击事件
//    private void mouseRightButtonClick(java.awt.event.MouseEvent evt) {
//        //判断是否为鼠标的BUTTON3按钮，BUTTON3为鼠标右键
//        if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
//            //通过点击位置找到点击为表格中的行
//            int focusedRowIndex = this.rowAtPoint(evt.getPoint());
//            if (focusedRowIndex == -1) {
//                return;
//            }
//            //将表格所选项设为当前右键点击的行
//            this.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
//            //弹出菜单
//            m_popupMenu.show(this, evt.getX(), evt.getY());
//        }
//
//    }


}
