package com.akalin.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * ���ݿ������
 * ���ز���˵����0������ʧ�ܣ�1:ִ�гɹ��� 2���޷��������ݿ⣻3��sql������ 
 * @author scatlet
 *
 */
public class DAO {
	
	//ִ���������
	public int add(String sql){
		try{
			Conn conn=new Conn();
			if(conn.getConnection()){
				conn.getState();
				conn.getStatement().executeUpdate(sql);//ִ��sql���
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
	
	//ִ��ɾ������
	public int delete(String sql){
		try{
			Conn conn=new Conn();
			if(conn.getConnection()){
				conn.getState();
				conn.getStatement().executeUpdate(sql);//ִ��sql���
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
	
	//ִ���޸Ĳ���
	public int modify(String sql){
		try{
			Conn conn=new Conn();
			if(conn.getConnection()){
				conn.getState();
				conn.getStatement().executeUpdate(sql);//ִ��sql���
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
	
	
	//�����Ǹ��ֲ�ѯ����
	/**
	 * ��ѯ�������ر���ָ���е�����
	 * @param sql ��ѯ���
	 * @param x ����Ҫ��ѯ��ָ���е�����
	 * @return
	 */
	public List<List<Object>> query(String sql,int[] x){
		List<List<Object>> list=new ArrayList<List<Object>>();
		List<Object> ls=new ArrayList<Object>();
		try{
			Conn conn=new Conn();
			if(conn.getConnection()){
				conn.getState();
				ResultSet set=conn.getStatement().executeQuery(sql);//ִ��sql���
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
	 * ��ѯ��
	 * @param sql ��ѯ���
	 * @param values �������е�����
	 * @param key ����Ҫ���ر��еļ�ֵ
	 * @return
	 */
	public List<Map<String,Object>> query(String sql,String[] values, String[] key){
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> map=new HashMap<String, Object>();
		try{
			Conn conn=new Conn();
			if(conn.getConnection()){
				conn.getState();
				ResultSet set=conn.getStatement().executeQuery(sql);//ִ��sql���
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
	 * ��ѯ��
	 * @param sql ��ѯ���
	 * @param str ��ѯ����
	 * @return
	 */
	public List<List<Object>> query(String sql,String[] str){
		List<List<Object>> list=new ArrayList<List<Object>>();
		List<Object> ls=new ArrayList<Object>();
		try{
			Conn conn=new Conn();
			if(conn.getConnection()){
				conn.getState();
				ResultSet set=conn.getStatement().executeQuery(sql);//ִ��sql���
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
