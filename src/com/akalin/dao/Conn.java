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

	//������ݿ�����
	public boolean getConnection(){
		try{
			String driver="com.mysql.jdbc.Driver";			//���ݿ�ӿ�����
			String url="jdbc:mysql://127.0.0.1/zuoye";	//���ݿ����ӵ�ַ
			String user="root";							//���ݿ������û���
			String password="12345";						//���ݿ���������
			Class.forName(driver);
			this.setConnection(DriverManager.getConnection(url, user, password));
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//��������ݿⷢ��sql���Ķ���
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
	
	//�ر����ݿ�����
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
