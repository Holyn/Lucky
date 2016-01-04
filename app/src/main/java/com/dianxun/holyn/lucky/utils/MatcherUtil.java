package com.dianxun.holyn.lucky.utils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by holyn on 2016/1/4.
 */
public class MatcherUtil {
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches() + "---");
        return m.matches();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(MatcherUtil.isMobileNO("12016155153"));
    }

}
