package com.swing.jxc.demo.swing.utils;

/**
 * @description: table分页测试
 * @author: liujun 249489478@qq.com
 * @create: 2019-07-05 09:48
 */

import com.swing.jxc.demo.swing.model.Buy;
import com.swing.jxc.demo.swing.model.Machine;
import com.swing.jxc.demo.swing.model.Sell;
import com.swing.jxc.demo.swing.model.UserInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
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

    public TablePage(String[] columnNames,List<T> list) {
        this.columnNames = columnNames;
        this.columnNum = columnNames.length;
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
                if(t instanceof  Buy){
                    Buy buy = (Buy) t;
//                    Object[] a = { buy.getId(),buy.getModel(),buy.getAmount()};// 把List**的数据赋给Object数组
                    Object[] a = {buy.getModel(),buy.getAmount()};// 把List**的数据赋给Object数组
                    data[i] = a;// 把数组的值赋给二维数组的一行
                }
                //用户
                if(t instanceof UserInfo){
                    UserInfo user = (UserInfo) t;
                    Object[] a = { user.getId(),user.getLoginName(),user.getUserName()};// 把List**的数据赋给Object数组
                    data[i] = a;// 把数组的值赋给二维数组的一行
                }
                //出库
                if(t instanceof Sell){
                    Sell sell = (Sell) t;
                    Object[] a = { sell.getId(),sell.getModel(),sell.getAmount()};// 把List**的数据赋给Object数组
                    data[i] = a;// 把数组的值赋给二维数组的一行
                }
                //设备
                if(t instanceof Machine){
                    Machine machine = (Machine) t;
                    Object[] a = { machine.getId(),machine.getName()};// 把List**的数据赋给Object数组
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
            model = new DefaultTableModel(getPageData(), columnNames);
        } else {
            // 如果结果集中没有数据，那么就用空来代替数据集中的每一行
            Object[][] nothing = { {}, {}, {}, {}, {} };
            model = new DefaultTableModel(nothing, columnNames);
            totalRowCount = 0;
        }
        this.setModel(model);
        this.setRowHeight(30);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
//        r.setHorizontalAlignment(JLabel.LEFT);
        setDefaultRenderer(Object.class, r);
    }
}