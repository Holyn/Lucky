package com.dianxun.holyn.lucky.model.http;

/**
 * Created by holyn on 2015/12/10.
 */
public class HttpURL {
    public final static String HTTP = "http://";
    public final static String HTTPS = "https://";

    /* 服务器地址 http://115.28.54.56/xingyun/ */
    public final static String HOST = HTTP+"115.28.54.56";
    public final static String URL_API_PRE = HOST+"/xingyun/index.php/Api/";
    public final static String URL_PIC_PRE = HOST+"/xingyun/Upload/";

    /* 模块名称 */
    public final static String USER = "User/";//用户模块
    public final static String FOOD = "Food/";//商品模块
    public final static String BOOK = "Book/";//订单模块

    /**
     * 首页-商品列表
     * http://115.28.54.56/xingyun/index.php/Api/Food/listIndexFood
     */
    public final static String HOME_FOOD_LIST = URL_API_PRE + FOOD +"listIndexFood";

}
