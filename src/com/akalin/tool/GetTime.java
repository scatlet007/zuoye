package com.akalin.tool;

import java.text.DateFormat;
import java.util.Date;

public class GetTime {
	private String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public GetTime(){
		DateFormat df=DateFormat.getDateInstance(DateFormat.MEDIUM);
		this.time=df.format(new Date());
	}
}
