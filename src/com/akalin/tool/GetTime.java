package com.akalin.tool;

import java.text.DateFormat;
import java.util.Calendar;
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
		DateFormat df=DateFormat.getDateInstance(DateFormat.FULL);
		this.time=df.format(new Date());
		
		Date now = new Date();
	    Calendar cal = Calendar.getInstance();
	     
	    DateFormat d1 = DateFormat.getTimeInstance();
	    this.time=d1.format(now);
	}
}
