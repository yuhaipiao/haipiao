package com.haipiao.persist.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author wangjipeng
 */
public class DateTimeUtil {

    public static final String DATE_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前时间的前12小时
     * @return
     */
    public static String twelveHoursAgo(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 12);
        return dateToString(new Date(calendar.getTime().getTime()));
    }

    public static String dateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_DATETIME);
        return formatter.format(date);
    }


}
