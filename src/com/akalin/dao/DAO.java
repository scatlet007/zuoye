package com.akalin.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 数据库操作类
 * 返回参数说明：0：操作失败；1:执行成功； 2：无法连接数据库；3：sql语句出错 
 * @author scatlet
 *
 */
public class DAO {
	
	//执行增添操作
	public int add(String sql){
		try{
			Conn conn=new Conn();
			if(conn.getConnection()){
				conn.getState();
				conn.getStatement().executeUpdate(sql);//执行sql语句
				conn.close();
				return 1;
			}else{
				conn.close();
				return 2;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	//执行删除操作
	public int delete(String sql){
		try{
			Conn conn=new Conn();
			if(conn.getConnection()){
				conn.getState();
				conn.getStatement().executeUpdate(sql);//执行sql语句
				conn.close();
				return 1;
			}else{
				conn.close();
				return 2;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	//执行修改操作
	public int modify(String sql){
		try{
			Conn conn=new Conn();
			if(conn.getConnection()){
				conn.getState();
				conn.getStatement().executeUpdate(sql);//执行sql语句
				conn.close();
				return 1;
			}else{
				conn.close();
				return 2;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	
	//以下是各种查询操作
	/**
	 * 查询表单，返回表单中指定列的数据
	 * @param sql 查询语句
	 * @param x 包含要查询的指定列的数组
	 * @return
	 */
	public List<List<Object>> query(String sql,int[] x){
		List<List<Object>> list=new ArrayList<List<Object>>();
		List<Object> ls=new ArrayList<Object>();
		try{
			Conn conn=new Conn();
			if(conn.getConnection()){
				conn.getState();
				ResultSet set=conn.getStatement().executeQuery(sql);//执行sql语句
				while(set.next()){
					for(int i=0;i<x.length;i++)
						ls.add(set.getString(x[i]));
					list.add(ls);
				}
				set.close();
				conn.close();
			}else{
				conn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 查询表单
	 * @param sql 查询语句
	 * @param values 包含表单中的列名
	 * @param key 包含要返回表单中的键值
	 * @return
	 */
	public List<Map<String,Object>> query(String sql,String[] values, String[] key){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=new HashMap<String, Object>();
		try{
			Conn conn=new Conn();
			if(conn.getConnection()){
				conn.getState();
				ResultSet set=conn.getStatement().executeQuery(sql);//执行sql语句
				int c=0;
				while(set.next()){
					for(int i=0;i<key.length;i++){
						map.put(key[i]+c, set.getString(values[i]));
					}
					c++;
					list.add(map);
				}
				set.close();
				conn.close();
			}else{
				conn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 查询表单
	 * @param sql 查询语句
	 * @param str 查询列名
	 * @return
	 */
	public List<List<Object>> query(String sql,String[] str){
		List<List<Object>> list=new ArrayList<List<Object>>();
		List<Object> ls=new ArrayList<Object>();
		try{
			Conn conn=new Conn();
			if(conn.getConnection()){
				conn.getState();
				ResultSet set=conn.getStatement().executeQuery(sql);//执行sql语句
				while(set.next()){
					for(int i=0;i<str.length;i++)
						ls.add(set.getString(str[i]));
					list.add(ls);
				}
				set.close();
				conn.close();
			}else{
				conn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
}
