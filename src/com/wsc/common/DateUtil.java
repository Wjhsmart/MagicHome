package com.wsc.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/**
	 * ��ȡ����ǰʱ��
	 * @return
	 */
	public static Date getDate() {
		return Calendar.getInstance().getTime();
	}
	
	/**
	 * ��ȡ��ǰʱ������Ӧ���ַ���
	 * @param date
	 * @return
	 */
	public static String getDateStr(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(date);
	}
	
}
