package wang.wongxd.wquicklib.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wongxd on 2017/8/16.
 */

public class TimeUtil {

    /**
     * 调用此方法输入所要转换的时间戳输入例如（14660689144）输出（"2016年6月16日 17时21分"）
     *
     * @param time
     * @return
     */
    public static String times(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        String times = sdr.format(new Date(lcc));
        return times;
    }

    /**
     * 根据传入时间戳 ，得出是 今天，昨天，星期几，那个具体时间
     *
     * @param time * @return
     */
    public static String formatDateTime(String time) {
        SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (time == null || "".equals(time)) {
            return "";
        }
        Date operateDate = null;
        try {
            operateDate = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar current = Calendar.getInstance();

        Calendar today = Calendar.getInstance();    //今天
        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
        //  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        Calendar yesterday = Calendar.getInstance();    //昨天
        yesterday.set(Calendar.YEAR, current.get(Calendar.YEAR));
        yesterday.set(Calendar.MONTH, current.get(Calendar.MONTH));
        yesterday.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH) - 1);
        yesterday.set(Calendar.HOUR_OF_DAY, 0);
        yesterday.set(Calendar.MINUTE, 0);
        yesterday.set(Calendar.SECOND, 0);

        Calendar oneWeek = Calendar.getInstance();    //一个礼拜以内
        oneWeek.set(Calendar.YEAR, current.get(Calendar.YEAR));
        oneWeek.set(Calendar.MONTH, current.get(Calendar.MONTH));
        oneWeek.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH) - 7);
        oneWeek.set(Calendar.HOUR_OF_DAY, 0);
        oneWeek.set(Calendar.MINUTE, 0);
        oneWeek.set(Calendar.SECOND, 0);

        current.setTime(operateDate);
        if (current.after(today)) {
            return "今天";
        } else if (current.before(today) && current.after(yesterday)) {
            return "昨天";
        } else if (current.before(yesterday) && current.after(oneWeek)) {
            //一周第一天是否为星期天
            boolean isFirstSunday = (current.getFirstDayOfWeek() == Calendar.SUNDAY);
            //获取周几
            int weekDay = current.get(Calendar.DAY_OF_WEEK);
            //若一周第一天为星期天，则-1，即中国时间
            if (isFirstSunday) {
                weekDay = weekDay - 1;
                if (weekDay == 0) {
                    weekDay = 7;
                }
            }
            switch (weekDay) {
                case 1:
                    return "星期一";
                case 2:
                    return "星期二";
                case 3:
                    return "星期三";
                case 4:
                    return "星期四";
                case 5:
                    return "星期五";
                case 6:
                    return "星期六";
                case 7:
                    return "星期天";
            }
        } else {
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");//24小时制
            String LgTime = sdformat.format(operateDate);
            return LgTime;
        }
        return "error";
    }
}
