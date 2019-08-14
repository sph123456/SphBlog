package com.soecode.blog.Untils;

import com.mysql.jdbc.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {
    public static long getDateByString(String str){
        if (StringUtils.isNullOrEmpty(str) == true)
            return 0;
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
        } catch (ParseException e) {
            return 0;
        }
        long ret = date.getTime();
        if (ret < 0)
            ret = 0;
        return ret;
    }

    //获取当前时间
    public static String getNowDateTime(){
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    }

    public static Date getDateByTime(long time) throws ParseException{
        Date d = new Date(time);
        return d;
    }

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static String getNowDateString() {
        return format.format(new Date());
    }

    public static void main(String[] args) throws ParseException {

//		Date d  = DateUtil.getDateByTime(Long.parseLong("1515568605110"));
//		System.out.println(d);
//        long date = getDateByString("0000-00-00");
//        System.out.println("date:"+date);

        System.out.println(getNowDateTime());
    }

    /**
     * 获取当前系统时间到指定时间的毫秒值；
     * @param hour
     * @param min
     * @param second
     * @return
     * @author monkey
     */
    public static Long getSecondsNextEarlyMorning(Integer hour, Integer min, Integer second) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.SECOND, min);
        cal.set(Calendar.MINUTE, second);
        cal.set(Calendar.MILLISECOND, 0);
        Long seconds = (cal.getTimeInMillis() - System.currentTimeMillis());
        return seconds.longValue();
    }

    //获得距离下日0点0分0秒的时间间隔，秒为单位
    public static int getSecondsEndOfDay(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        int seconds = (int)((cal.getTimeInMillis() - System.currentTimeMillis())/1000);
        return seconds;
    }

    // 获得某天最大时间 2017-10-15 23:59:59
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    // 获得某天最小时间 2017-10-15 00:00:00
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取到明天的某个时间点的时间戳
     * @param addTime
     * @return
     */
    public static long addTimeByEndOfDay(long addTime){
        Date date = getEndOfDay(new Date());
        return date.getTime() + addTime + 1;
    }

    /**
     * 获取到明天的某个时间点的秒数
     * @param addTime
     * @return
     */
    public static int addSecondByEndOfDay(long addTime){
        return (int) (addTimeByEndOfDay(addTime) / 1000);
    }

    /**
     * 获取下一个minuteInterval间隔的时间戳
     * @param minuteInterval 范围1~60分钟
     * @return
     */
    static public long getNextMinuteTimestamp(int minuteInterval){
        Calendar calendar = Calendar.getInstance();
        int minute = calendar.get(Calendar.MINUTE);
        minute = (minute/minuteInterval+1)*minuteInterval;
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        long timestamp = calendar.getTimeInMillis();
        return timestamp;
    }

    /**
     * 获取距离下个5分钟，10分钟间隔，单位秒，用于redis缓存失效时间
     * @param minuteInterval 范围1~60分钟
     * @return
     */
    static public long getNextMinuteDistance(int minuteInterval){
        long now = System.currentTimeMillis();
        long next = getNextMinuteTimestamp(minuteInterval);
        long dis = (next-now)/1000;
        return dis;
    }

    public static int timestamp2Date(int timestamp){
        Calendar calendar = Calendar.getInstance();
        long time = timestamp*1000l;
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND, 0);
        return (int)(calendar.getTimeInMillis()/1000);
    }

    //获得明天的0时0分0秒时间戳(秒)
    public static int getNextDayTimestamp(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (int)(cal.getTimeInMillis()/1000);
    }
    /**
     * 将标准时间转成 时间戳
     *
     */
    public static Date getFormatNow(String string) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sf.parse(string);
        } catch (ParseException e) {
        }
        return date;

    }
}
