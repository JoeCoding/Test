package com.neuedu.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DButil {

	public DButil() {
		super();
		// TODO Auto-generated constructor stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//获取数据库连接
	public Connection getconn() throws ClassNotFoundException, SQLException{
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@10.25.49.37:1521:orcl", "scott", "tiger");
		return conn;
	}
	
	//开启事务
	public void beginTransaction(Connection conn) throws SQLException{
		conn.setAutoCommit(false);
	}
	
	//提交事务
	public void commit(Connection conn) throws SQLException{
		conn.commit();
	}
	//回滚
	public void rollback(Connection conn) throws SQLException{
		conn.rollback();
	}
	
	//关闭连接
	public void closeConn(Connection conn) throws SQLException{
		conn.close();
	}
	
	//关闭PS
	public static void closePS(PreparedStatement ps) throws SQLException{
		ps.close();
	}
}
