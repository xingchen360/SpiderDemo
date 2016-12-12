package com.noteshare.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
	private static String driver = null;
	private static String url = null;
	private static String user = null;
	private static String pwd = null;
	static{
		Config config = new Config("db.properties");
		driver = config.parseString("driver");
		url = config.parseString("url");
		user = config.parseString("user");
		pwd = config.parseString("pwd");
	}
	public static Connection getConnection() throws Exception{
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,pwd);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return con;
	}
	/**
	 * 
	 * @return	
	 * @since   2016年4月20日
	 * @version V0.2.0
	 */
	public static Connection getConn(){
		Connection conn = null;
		try {
			Context ct = new InitialContext();
			Context envContext = (Context) ct.lookup("java:comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/default");
			conn = dataSource.getConnection();
		} catch (NamingException e) {
			System.out.println("数据源没找到！");
			e.printStackTrace();
		} catch (SQLException e) {
			 System.out.println("获取数连接对象失败！");
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeCon(Connection con) throws SQLException{
		if(con !=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
	}
	public static void closeStmt(Statement stmt) throws SQLException{
		if(stmt !=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
	}
	public static void closeRs(ResultSet rs) throws SQLException{
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
	}
	
	
	public static void main(String[] args) {
		try {
			Connection conn = getConnection();
			System.out.println(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
