package com.akalin.tool;

import java.text.DateFormat;
import java.util.Date;

public class GetDate {
	private String dateString;

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	
	public GetDate(){
		DateFormat df=DateFormat.getDateInstance(DateFormat.LONG);//���峤���ڸ�ʽ����
		this.dateString=df.format(new Date());//��ø�ʽ�������ڶ���
	}
}
