package com.cbx.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 完成字符串跟日期之间的转换
 */
public class DateUtils {
//    日期转换成字符串
      public static String date2String(Date date,String pattern){
          SimpleDateFormat format=new SimpleDateFormat(pattern);
          String format1 = format.format(date);
          return format1;
      }
    //字符串转换为日期
    public static Date string2Date(String str,String pattern) throws ParseException {
          SimpleDateFormat format=new SimpleDateFormat(pattern);
        Date parse = format.parse(str);
        return parse;
    }
}
