package com.mk.taskfactory.biz.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    static String DATE_NULL = "0000-00-00";

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

    /**
     * �ж� compareday  �Ƿ�����������֮��
     * @param startdateStr   ��ʼʱ��
     * @param compareday     ���Ƚϵ�ʱ��
     * @param enddateStr    ����ʱ��
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
     * ��ȡ�������ڵĲ�ֵ
     * @param smdate
     * @param bdate
     * @return  'bdate' - 'smdate'���ڲ�
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
     * �Ƚ������������͵�String��С
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
     * ʱ��Date����ת��Ϊ��������
     * @param date  Ҫת����ʱ������
     * @param example  ת����ĸ�ʽ
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
     * �õ������ַ���
     *  exmple �������ڸ�ʽ
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
}
