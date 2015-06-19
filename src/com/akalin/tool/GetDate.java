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
		DateFormat df=DateFormat.getDateInstance(DateFormat.LONG);//定义长日期格式对象
		this.dateString=df.format(new Date());//获得格式化的日期对象
	}
}
