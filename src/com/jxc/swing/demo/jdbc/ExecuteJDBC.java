package com.jxc.swing.demo.jdbc;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Description
 * @Author liujun
 * @Date 2019/5/20 0020
 */
public class ExecuteJDBC {
	private static Connection con = null;// 创建一个数据库连接
	private static PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
	private static ResultSet result = null;// 创建一个结果集对象

	/**
	 * 连接
	 *
	 * @return
	 */
//	public static Connection getContent() {
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
////			System.out.println("开始尝试连接数据库！");
////			String url = "jdbc:oracle:" + "thin:@10.0.188.8:13521:sccenter";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
////			String user = "dcbusiness";// 用户名,系统默认的账户名
////			String password = "dcbusiness";// 你安装时选设置的密码
//
//			String url = "jdbc:oracle:" + "thin:@10.0.188.9:15211:tianque";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
//			String user = "lsgridtest";// 用户名,系统默认的账户名
//			String password = "lsgridtest";// 你安装时选设置的密码
//			con = DriverManager.getConnection(url, user, password);// 获取连接
////			System.out.println("连接成功！");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return con;
//	}


	/**
	 * 关闭连接
	 */
	public static void close() {
		try {
			// 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
			// 注意关闭的顺序，最后使用的最先关闭
			if (result != null) {
				result.close();
			}
			if (pre != null) {
				pre.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	static {
//		getContent();
//	}
	public static boolean executeJdbc(String sql, String fileName) {
		boolean sucess = false;
		Connection conn=null;
		PreparedStatement pstmt = null;
		//1.创建自定义连接池对象
		MyDataSource dataSource = new MyDataSource();
		try {
			//2.从池子中获取连接
			conn=dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			int rows = pstmt.executeUpdate();
			System.out.println("执行返回结果："+rows);
//			if(rows>0) {
//				System.out.println("添加成功！"+rows);
//			}else {
//				System.out.println("添加失败！");
//			}

		} catch (Exception e) {
			failFile(fileName+"\r\n"+e.getMessage());
//			throw new RuntimeException(e);
			e.printStackTrace();
		}finally {
			dataSource.backConnection(conn);
		}
		return sucess;
	}

	private static void failFile(String fileName) {
//		BufferedWriter out = null;
//		try {
//			out = new BufferedWriter(new OutputStreamWriter(
//					new FileOutputStream("D:\\test\\sql\\batch_create_sql\\JDBC执行失败\\fail.txt", true)));
//			out.write(fileName+"\r\n");
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				out.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
	}

}
