package com.chinasofti.ordersys.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDao {

	private String DRIVER = "com.mysql.jdbc.Driver";
	
	private String URL = "jdbc:mysql://localhost:3306/ordersys?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
	
	private String USER = "root";
	
	private String PASS = "";
	
	protected Connection con;
	
	public BaseDao() {
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			con = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
