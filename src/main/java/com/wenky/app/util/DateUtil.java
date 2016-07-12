package com.wenky.app.util;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class DateUtil {

	public static  String getTimeStamp() {
		SimpleDateFormat sdf=new SimpleDateFormat();
		sdf.getTimeInstance();
		return sdf.getTimeInstance().toString();
	}

	public static String getTimeStamp(Date date) {
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(DateUtil.getTimeStamp());
	}

}

class DateComparator implements Comparator{

	@Override
	public int compare(Object paramT1, Object paramT2) {
		if(!(PreConditions.isEmpty(paramT1)|| PreConditions.isEmpty(paramT2))){
			return ((Date)paramT2).compareTo((Date) paramT1);
		}
		
		return -1;
	}
	
}