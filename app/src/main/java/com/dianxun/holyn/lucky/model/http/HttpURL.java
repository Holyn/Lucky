package com.dianxun.holyn.lucky.model.http;

/**
 * Created by holyn on 2015/12/10.
 */
public class HttpURL {
    public final static String HTTP = "http://";
    public final static String HTTPS = "https://";
    public final static String ONE_SPRIT = "/";

    /* 服务器地址 http://115.28.54.56/xingyun/ */
    public final static String HOST = HTTP+"115.28.54.56";
    public final static String URL_API_PRE = HOST+"/xingyun/index.php/Api/";
    public final static String URL_PIC_PRE = HOST+"/xingyun/Upload/";

    /* 模块名称 */
    public final static String USER = "User/";//用户模块
    public final static String FOOD = "Food/";//商品模块
    public final static String BOOK = "Book/";//订单模块

    /* 一些参数 */
    public final static String PARAM_ID = "id/";
    public final static String PARAM_TEL = "tel/";
    public final static String PARAM_PASSWORD = "password/";
    public final static String PARAM_NAMW = "name/";
    public final static String PARAM_OLD_PASSWORD = "oldPassword/";
    public final static String PARAM_FOOD_ID = "foodId/";
    public final static String PARAM_USER_ID = "userId/";

    /**
     * 商品-列表
     * http://115.28.54.56/xingyun/index.php/Api/Food/listIndexFood
     */
    public final static String FOOD_LIST = URL_API_PRE + FOOD +"listIndexFood";

    /**
     * 商品-详情
     * http://115.28.54.56/xingyun/index.php/Api/Food/getInfo
     */
    public final static String FOOD_DETAIL = URL_API_PRE + FOOD +"getInfo";

    /**
     * 商品-房间列表
     * http://115.28.54.56/xingyun/index.php/Api/Food/listRoom
     */
    public final static String FOOD_ROOM_LIST = URL_API_PRE + FOOD +"listRoom/p/";

    /**
     * 商品-针对某个商品新建房间
     * http://115.28.54.56/xingyun/index.php/Api/Food/addRoom
     */
    public final static String FOOD_ROOM_ADD = URL_API_PRE + FOOD +"addRoom";

}
