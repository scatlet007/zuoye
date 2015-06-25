package com.test;

import java.util.List;

import com.akalin.dao.DAO;

public class Test {
	
	public static void main(String[] args){
		DAO dao=new DAO();
		int x[]={1,2,3};
		List<List<Object>> list=dao.query("select * from admin;", x);
		for(List<Object> l:list){
			for(Object obj:l){
				System.out.print(obj+"\t");
			}
		}
	}
}
