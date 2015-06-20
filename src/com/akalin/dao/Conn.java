package com.akalin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
	private Connection connection;
	private Statement statement;
	
	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	

	public Statement getStatement() {
		return statement;
	}


	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	//获得数据库连接
	public boolean getConnection(String driver,String url,String user,String password){
		try{
			Class.forName(driver);
			this.setConnection(DriverManager.getConnection(url, user, password));
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//获得向数据库发送sql语句的对象
	public boolean getState(){
		try{
			if(null!=this.connection)
				this.setStatement(this.connection.createStatement());
			else
				return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//关闭数据库连接
	public void close(){
		try{
			if(null!=this.statement)
				this.statement.close();
			if(null!=this.connection)
				this.connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
