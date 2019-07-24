package czt_ssm_utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String Date2String(Date date , String patt){
        SimpleDateFormat str = new SimpleDateFormat(patt);
        String format = str.format(date);
        return format;
    }

    public static Date String2Date(Date date , String patt) throws ParseException{
        SimpleDateFormat str = new SimpleDateFormat(patt);
        Date parse = str.parse(String.valueOf(str));
        return parse;

    }

}
