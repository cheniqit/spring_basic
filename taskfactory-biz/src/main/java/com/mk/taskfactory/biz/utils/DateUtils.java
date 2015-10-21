package com.mk.taskfactory.biz.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;
import org.joda.time.Seconds;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    static String DATE_NULL = "0000-00-00";
    public static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String format_yMd(Date date) {
        if (date == null) {
            return DATE_NULL;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String format_yMdHms(Date date) {
        if (date == null) {
            return DATE_NULL;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static Date parse(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(strDate);
        } catch (Exception e) {
            //log
        }
        return null;
    }

    public static Date parse(String strDate, String formatStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        try {
            return sdf.parse(strDate);
        } catch (Exception e) {
            //log
        }
        return null;
    }

    /**
     * 判断 compareday  是否在两个日期之间
     * @param startdateStr   起始时间
     * @param compareday     被比较的时间
     * @param enddateStr    结束时间
     * @return    true /  false
     * @throws ParseException
     */
    public  static  boolean   dayBetween(String startdateStr,String  compareday,String enddateStr) throws ParseException {
        if (getCompareResult(compareday,startdateStr,"yyyy-MM-dd")){
            return  false;
        }
        if (!getCompareResult(compareday, enddateStr,"yyyy-MM-dd")){
            return  false;
        }
        return  true;
    }


    /**
     * 获取两个日期的差值
     * @param smdate
     * @param bdate
     * @return  'bdate' - 'smdate'日期差
     * @throws ParseException
     */
    public static int daysBetween(String bdate,String smdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 比较两个日期类型的String大小
     * @param dataA
     * @param dataB
     * @return
     * @throws ParseException
     */
    public static boolean getCompareResult(String dataA,String dataB,String  example) throws ParseException {
        if(StringUtils.isEmpty(example)){
            example = "yyyy-MM-dd";
        }
        DateFormat dafShort=new SimpleDateFormat(example);
        Date a=dafShort.parse(dataA);
        Date b=dafShort.parse(dataB);
        return a.before(b);
    }


    /**
     * 时间Date类型转换为日期类型
     * @param date  要转换的时间类型
     * @param example  转换后的格式
     */
    public static String dateToString(Date date,String  example){
        if (null == date) {
            return null;
        }
        if(StringUtils.isEmpty(example)){
            example = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(example);
        return sdf.format(date);
    }

    public static String getTime(String  example) {
        if(StringUtils.isEmpty(example)){
            example = "HH:mm:ss";
        }
        Calendar calendar = Calendar.getInstance();
        Date d = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(example);
        return sdf.format(d);
    }

    /**
     * 得到日期字符串
     *  exmple 返回日期格式
     * @return
     */
    public static String getStringDate(String  exmple) {
        if(null==exmple||exmple.trim().length()==0){
            exmple = "yyyy-MM-dd HH:mm:ss";
        }
        Date dt = new Date();
        SimpleDateFormat matter1 = new SimpleDateFormat(exmple);
        String result = matter1.format(dt);
        return result;
    }
    // < -1 = 0 > 1
    public static int compareDate(Date day1, Date day2) {
        LocalDateTime sysExTime = LocalDateTime.fromDateFields(day1);
        LocalDateTime startExTime = LocalDateTime.fromDateFields(day2);
        int st = Seconds.secondsBetween(sysExTime, startExTime).getSeconds();
        return st;
    }

}
