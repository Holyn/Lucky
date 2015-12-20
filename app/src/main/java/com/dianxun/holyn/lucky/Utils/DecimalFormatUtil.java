package com.dianxun.holyn.lucky.Utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by holyn on 2015/12/18.
 */
public class DecimalFormatUtil {
    /** 求小数点后两位 要四舍五入 */
    public static String getAfterTwo(double num){
        DecimalFormat df   = new DecimalFormat();
        df.setMaximumFractionDigits(2);//多少位
//        df.setGroupingSize(0);
        return  df.format(num);
    }
    /** 求小数点后两位 不四舍五入 */
    public static String getAfterTwoFloor(double num){
        DecimalFormat df   = new DecimalFormat();
        df.setMaximumFractionDigits(2);//多少位
        df.setGroupingSize(0);
        df.setRoundingMode(RoundingMode.FLOOR);//不要四舍五入
        return  df.format(num);
    }
}
