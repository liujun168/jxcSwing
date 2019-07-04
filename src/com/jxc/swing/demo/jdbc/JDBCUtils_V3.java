package com.jxc.swing.demo.jdbc;

/**
 * @Description
 * @Author liujun
 * @Date 2019/5/21 0021
 */

import java.sql.*;

/**
 * 提供获取连接和释放资源的 方法
 */
public class JDBCUtils_V3 {

	/**
	 * 获取连接方法
	 *
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web08", "root", "ao1221");

			Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
//			String url = "jdbc:oracle:" + "thin:@10.0.188.8:13521:sccenter";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
//			String user = "dcbusiness";// 用户名,系统默认的账户名
//			String password = "dcbusiness";// 你安装时选设置的密码

//			String url = "jdbc:oracle:" + "thin:@10.0.188.9:15211:tianque";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
//			String user = "lsgridtest";// 用户名,系统默认的账户名
//			String password = "lsgridtest";// 你安装时选设置的密码

//	        String user = "leshan_dataCenter";// 用户名,系统默认的账户名
//			String password = "leshan_dataCenter";// 你安装时选设置的密码


			String url = "jdbc:oracle:" + "thin:@10.0.188.8:13521:sccenter";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
			String user = "leshan";// 用户名,系统默认的账户名
			String password = "leshan";// 你安装时选设置的密码
			conn = DriverManager.getConnection(url, user, password);// 获取连接
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void release(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
