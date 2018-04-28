package com.spring.worldwire.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by luxun on 2018/4/28.
 */
public class DateUtil {

    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 计算两个日期相差的天数
     * @param start 开始日期
     * @param end   结束日期
     * @return
     * @throws ParseException
     */
    public static int dateInterval(Date start, Date end) throws ParseException {

        return (int) ((df.parse(df.format(end)).getTime() - df.parse(df.format(start)).getTime())/(3600 * 24 * 1000));


    }
}
