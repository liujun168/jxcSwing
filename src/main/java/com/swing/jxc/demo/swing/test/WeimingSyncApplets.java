package com.swing.jxc.demo.swing.test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static javax.swing.ListSelectionModel.SINGLE_SELECTION;

/**
 * @author: lishuai
 * @date: 2018/11/26 13:51
 */
public class WeimingSyncApplets {
    public static void main(String[] args) {
        // 面板组件
        JPanel taskPanel = new JPanel();
        JPanel dbPanel = new JPanel();
        JTabbedPane tabbedPane = buildJTabbedPane(taskPanel, dbPanel);
        buildFrame(tabbedPane);
    }

    private static JTabbedPane buildJTabbedPane(JPanel taskPanel, JPanel dbPanel) {
        // 选项卡面板
        JTabbedPane tabbedPane = new JTabbedPane();
        // 通过BorderFactory来设置边框的特性
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tabbedPane.add("执行任务", taskPanel);
        tabbedPane.add("数据源配置", dbPanel);

        // 创建面板容器
        JPanel tablePanel = new JPanel();
        // 设置BorderLayout布局方式
        tablePanel.setLayout(new BorderLayout());
        // 创建表格
        JTable table = configTable();
        // 使用普通的中间容器添加表格时，表头 和 内容 需要分开添加
        tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
        tablePanel.add(table, BorderLayout.CENTER);
        // 将容器放入taskPanel面板
        taskPanel.add(tablePanel);

        return tabbedPane;
    }

    private static JTable configTable() {
        // 创建 table
        JTable table = getTable();
        // 获取 model
        DefaultTableModel model = getTableModel();
        table.setModel(model);
        for (int i = 0; i < model.getRowCount(); i++) {
            // 获得表格的值
            String taskName = (String) model.getValueAt(i, 0);
            // 设置表格的值
            model.setValueAt(taskName + "执行时间:" + i + "分钟后", i, 2);
        }
        int[] columnWidth = {135, 165, 350, 150, 150, 100};
        for (int i = 0; i < columnWidth.length; i++) {
            // 设置表格各栏各行的尺寸
            table.getColumnModel().getColumn(i).setPreferredWidth(columnWidth[i]);
        }
        return table;
    }

    private static JTable getTable() {
        JTable table = new JTable() {
            @Override
            public void updateUI() {
                // 刷新
                super.updateUI();
                // 表格行高
                setRowHeight(36);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                // 第3列、第4列设置可以编辑，其余列不可编辑
                if (column != 4 && column != 5) {
                    return false;
                }
                return true;
            }
        };
        // 设置表头不可移动
        table.getTableHeader().setReorderingAllowed(false);
        // 一次只能选择一项
        table.setSelectionMode(SINGLE_SELECTION);
        return table;
    }

    private static DefaultTableModel getTableModel() {
        int rowCount = 4;
        Object[] columnNames = {"任务名称", "执行规则", "任务执行时间", "状态", "操作", "SQL"};
        Object[][] rowData = new Object[rowCount][columnNames.length];
        // 向表格中填充数据
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnNames.length; j++) {
                switch (j) {
                    case 0:
                        rowData[i][j] = "任务" + i;
                        break;
                    case 1:
                        rowData[i][j] = "0 0 0/" + i + " * *  ";
                        break;
                    default:
                        rowData[i][j] = null;
                }
            }
        }
        return new DefaultTableModel(rowData, columnNames);
    }


    private static void buildFrame(JComponent component) {
        // 窗体容器
        JFrame frame = new JFrame("数据同步工具");
        frame.add(component);
        //  JFrame.EXIT_ON_CLOSE  退出
        //  JFrame.HIDE_ON_CLOSE  最小化隐藏
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        // 设置布局
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(BorderLayout.CENTER, component);
        // 设置窗口最小尺寸
        frame.setMinimumSize(new Dimension(1060, 560));
        // 调整此窗口的大小，以适合其子组件的首选大小和布局
        frame.pack();
        // 设置窗口相对于指定组件的位置
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // 设置窗口尺寸是否固定不变
        frame.setResizable(true);
    }

}
