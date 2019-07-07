package com.swing.jxc.demo.swing.test;

import javax.swing.*;


public class JTableTest extends JFrame{
//    private JPanel jp1=new JPanel();
//    private JPanel jp2=new JPanel();
//    private JPanel jp3=new JPanel();
//
//
//    private JTable jt =null;
//    private JButton select =new JButton("查询");
//    private JButton delete =new JButton("删除");
//    private JButton update =new JButton("确认修改");
//    private JButton add =new JButton("增加");
//    private JButton recover =new JButton("恢复");
//    private JTextField jtf=new JTextField(20);
//    private JScrollPane jsp=null;
//    private DefaultTableModel dtm=null;
//    private StudentService ss=new StudentServiceImpl();
//    private List<Student> list=null;
//    private Vector<Vector<String>> vec1;
//    private Vector<String> vec2=null;
//    private String st=null;
//    //===========保存修改后的列名=====================
//    private Set<Integer> li=null;
//
//    public JTableTest() {
//        this.setTitle("学生表");
//        this.setSize(500, 600);
//        this.setLocationRelativeTo(null);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setResizable(false);
//        //============设置一个保存表头的Vector==========
//        vec2=new Vector<String>();
//        vec2.add("学号");
//        vec2.add("编号");
//        vec2.add("姓名");
//        vec2.add("分数");
//
//
//        list=ss.selectStudentAll();
//　　　　//
//        vec1=DataConversion.zhuanHuan(list);
//　　　　//============为表模型设置数据和表头=============
//        dtm=new DefaultTableModel(vec1,vec2);
//        jt=new JTable(){
//　　　　　　//===============设置数据是否可编辑================
//            public boolean isCellEditable(int row, int column) {
//　　　　　　//==============姓名、分数可编辑==================
//                if(column==StudentConstant.SNAME||column==StudentConstant.SCORE)
//                    return true;
//                else
//                    return false;
//            }
//
//        };
//        jt.setModel(dtm);
//　　　　　//===========设置表的点击方式为只可单选===============
//        jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//　　　　//================设置列不可移动=============
//        jt.getTableHeader().setReorderingAllowed(false);
//　　　　//===============设置所以列不可调整大小===================
//        jt.getTableHeader().setResizingAllowed(false);
//        //===============隐藏学号这一列因为学号不可被客户查看，但我们对数据的增删改查需要用到主键，所以隐藏===================
//        TableColumn tc=jt.getColumnModel().getColumn(0);
//        tc.setMaxWidth(0);
//        tc.setMinWidth(0);
//        tc.setPreferredWidth(0);
//        tc.setResizable(false);
//
//        li=new TreeSet<Integer>();
//
//        //===========查询==========================
//        select.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                String sname=jtf.getText();
//                list=new ArrayList<Student>();
//                list=ss.selectStudentBySname(sname);
//                flush();
//            }
//        });
//
//        //===============增加=====================
//        add.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//　　　　//=============new一个新的增加窗口=============
//                AddJFrame ad=new AddJFrame();
//　　　　//=============当主窗体获得焦点时刷新一下Jtable============
//                add.addFocusListener(new FocusAdapter() {
//                    public void focusGained(FocusEvent e) {
//                        list=ss.selectStudentAll();
//                        flush();
//                    }
//                });
//            }
//        });
//
//        //================删除=====================
//        delete.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                int i=jt.getSelectedRow();
//                //=======判断是否选中不选中不弹窗口==============
//                if(i!=-1)
//                {
//                    String s=(String) dtm.getValueAt(i, StudentConstant.SID);
//　　　　　　　　　　//=================弹确认删除窗口==================================
//                    int t=JOptionPane.showConfirmDialog(JTableTest.this, "是否删除该数据");
//                    switch (t) {
//                        case JOptionPane.YES_OPTION:
//                            ss.deleteStudent(Integer.parseInt(s));
//                            list=ss.selectStudentAll();
//                            flush();
//                    }
//                }
//
//            }
//        });
//
//        //=================修改数据====================
//        //==================获取修改前的数据===============
//        jt.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                int i=jt.getSelectedRow();
//                int j=jt.getSelectedColumn();
//                st = (String) jt.getValueAt(i, j);
//            }
//        });
//　　　　
//        dtm.addTableModelListener(new TableModelListener() {
//
//            public void tableChanged(TableModelEvent e) {
//                int i=jt.getSelectedRow();
//                int j=jt.getSelectedColumn();
//                String s= (String) jt.getValueAt(i, j);
//　　　　　　　//=================判断修改后数据有没有发生改变===============
//                if(e.getType()==TableModelEvent.UPDATE)
//                {
//                    if(st!=null&!st.equals(s))
//                    {
//　　　　　　　//================改变后就加入set集合里，注意此处使用set集合重复修改的也只会提交一次可以提高性能=====================
//                        li.add(i);
//                    }
//                }
//            }
//        });
//        //================确认修改============
//        update.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                int t=JOptionPane.showConfirmDialog(JTableTest.this, "你确认修改吗，修改后不可恢复");
//                switch (t) {
//　　　　　　//========================确认修改，在数据库中修改============================
//                    case JOptionPane.YES_OPTION:
//                        for (Integer i : li) {
//                            Student s=new Student();
//                            String ssid=(String) jt.getValueAt(i, StudentConstant.SID);
//                            int sid=Integer.parseInt(ssid);
//                            String sname=(String) jt.getValueAt(i, StudentConstant.SNAME);
//                            int score=Integer.parseInt((String) jt.getValueAt(i, StudentConstant.SCORE));
//                            s.setSid(sid);
//                            s.setSname(sname);
//                            s.setScore(score);
//                            ss.upadteStudent(s);
//                        }
//                        list=ss.selectStudentAll();
//                        flush();
//                }
//            }
//        });
//        //===================恢复====================
//        recover.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                int t=JOptionPane.showConfirmDialog(JTableTest.this, "你确认恢复吗，只可以恢复到上一次确认修改后的位置");
//                switch (t) {
//                    case JOptionPane.YES_OPTION:
//　　　　//==============确认修改之前只是在表上修改了数据没有对数据库进行操作，点击恢复只需要重新查询数据库刷新界面========================
//                        list=ss.selectStudentAll();
//                        flush();
//                }
//            }
//        });
//
//
//
//        jsp=new JScrollPane(jt);
//        jp1.add(jtf);
//        jp1.add(select);
//        jp2.add(jsp);
//        jp3.add(add);
//        jp3.add(delete);
//        jp3.add(recover);
//        jp3.add(update);
//        this.getContentPane().add(jp1, BorderLayout.NORTH);
//        this.getContentPane().add(jp2);
//        this.getContentPane().add(jp3, BorderLayout.SOUTH);
//
//
//        this.setVisible(true);
//    }
//    private void flush() {
//　　//==============改变模型的数据====================
////        vec1=DataConversion.zhuanHuan(list);
//        dtm=new DefaultTableModel(vec1,vec2);
//        jt.setModel(dtm);
//　　//======每次刷新的时候隐藏第一列=================
//        TableColumn tc=jt.getColumnModel().getColumn(0);
//        tc.setMaxWidth(0);
//        tc.setMinWidth(0);
//        tc.setPreferredWidth(0);
//        tc.setResizable(false);
//    }
//
//    public static void main(String[] args) throws SQLException {
//        new JTableTest();
//    }
}
