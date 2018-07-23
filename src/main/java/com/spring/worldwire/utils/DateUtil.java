package com.spring.worldwire.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Created by luxun on 2018/4/28.
 */
public class DateUtil {

    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 计算两个日期相差的时间
     * @param start 开始日期
     * @param end   结束日期
     * @return
     * @throws ParseException
     */
    public static int dateInterval(Date start, Date end) throws ParseException {
        if(Objects.isNull(start) || Objects.isNull(end)){
            return 10000;
        }

        return (int) ((df.parse(df.format(end)).getTime() - df.parse(df.format(start)).getTime())/(3600 * 24 * 1000));

    }

    /**
     * 计算当天还剩多长时间，单位是毫秒
     * @param date
     * @return
     */
    public static Long getTimeInterval(Date date){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR,23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,59);
        cal.set(Calendar.MILLISECOND,999);
        return cal.getTime().getTime() - date.getTime();
    }
}
