package com.show.util;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetDateUtil {
    public static String getDate(Date date){
        //获取当前时间
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dataFormat.format(new Date());
    }
    public String getDate(String format, Data data){
        //获取当前时间
        SimpleDateFormat dataFormat = new SimpleDateFormat(format);
        return dataFormat.format(new Date());
    }
}
