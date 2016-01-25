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
    public final static String COMPANY = "Company/";//店铺模块
    public final static String COMPANY_TYPE = "CompanyType/";//店铺类型模块

    /* 一些参数 */
    public final static String PARAM_ID = "id/";
    public final static String PARAM_PAGE = "p/";
    public final static String PARAM_TEL = "tel/";
    public final static String PARAM_PASSWORD = "password/";
    public final static String PARAM_NAMW = "name/";
    public final static String PARAM_OLD_PASSWORD = "oldPassword/";
    public final static String PARAM_FOOD_ID = "foodId/";
    public final static String PARAM_USER_ID = "userId/";
    public final static String PARAM_TYPE_ID = "typeId/";
    public final static String PARAM_ADDRESS = "address/";
    public final static String PARAM_PIC = "pic/";

    /* 一些错误信息 */
    public final static String MSG_TEL_ERROR = "telError";//电话号码已经注册
    public final static String MSG_OK = "ok";//注册成功
    public final static String MSG_ERROR = "error";

    /***********************************************************************************************
     * 店铺-获取商品分类的类型列表
     * http://115.28.54.56/xingyun/index.php/Api/Company/listCompanyType
     */
    public final static String COMPANY_CLASSIFY_TYPE_ID_LIST = URL_API_PRE + COMPANY +"listCompanyType";

    /***********************************************************************************************
     * 商品-首页列表
     * http://115.28.54.56/xingyun/index.php/Api/Food/listIndexFood
     */
    public final static String FOOD_LIST = URL_API_PRE + FOOD +"listIndexFood";

    /**
     * 商品-分类浏览商品列表
     * http://115.28.54.56/xingyun/index.php/Api/Food/listFood/typeId/5/p/1
     */
    public final static String FOOD_CLASSIFY__LIST = URL_API_PRE + FOOD +"listFood/p/";

    /**
     * 商品-详情
     * http://115.28.54.56/xingyun/index.php/Api/Food/getInfo/id/123
     */
    public final static String FOOD_DETAIL = URL_API_PRE + FOOD +"getInfo";

    /**
     * 商品-房间列表
     * http://115.28.54.56/xingyun/index.php/Api/Food/listRoom
     */
    public final static String FOOD_ROOM_LIST = URL_API_PRE + FOOD +"listRoom/p/";

    /**
     * 商品-针对某个商品新建房间
     * http://115.28.54.56/xingyun/index.php/Api/Food/addRoom/foodId/1/userId/41/password/123456
     */
    public final static String FOOD_ROOM_ADD = URL_API_PRE + FOOD +"addRoom/";

    /***********************************************************************************************
     * 用户-登录
     * http://115.28.54.56/xingyun/index.php/Api/User/doLogin/tel/13590604841/password/123456
     */
    public final static String USER_DO_LOGIN = URL_API_PRE + USER +"doLogin/";

    /**
     * 用户-注册
     * http://115.28.54.56/xingyun/index.php/Api/User/doReg/tel/13590604841/password/123456/name/holyn
     * name：真实姓名
     */
    public final static String USER_DO_REG = URL_API_PRE + USER +"doReg/";

    /**
     * 用户-获取验证码
     * http://115.28.54.56/xingyun/index.php/Api/User/getCode/tel/13590604841
     */
    public final static String USER_GET_CODE = URL_API_PRE + USER +"getCode/";

    /**
     * 用户-重置密码
     * http://115.28.54.56/xingyun/index.php/Api/User/doPassword/id/123/password/123/oldPassword/12345
     */
    public final static String USER_DO_PASSWORD = URL_API_PRE + USER +"doPassword/";

    /**
     * 用户-上传头像 （post方式）
     * http://115.28.54.56/xingyun/index.php/Api/User/upload
     * 文件的form字段是imgfile
     * 返回json里面带有图片的网络路径
     */
    public final static String USER_UPLOAD_IMG = URL_API_PRE + USER +"upload";

    /**
     * 用户-修改用户信息
     * http://115.28.54.56/xingyun/index.php/Api/User/update/id/123/name/holyn/tel/123/address/12345/pic/xxxxxxxxx
     * 成功后：返回用户Json
     */
    public final static String USER_UPDATE = URL_API_PRE + USER +"update/";

    /***********************************************************************************************
     * 订单-我的订单列表
     * http://115.28.54.56/xingyun/index.php/Api/Book/listBook/userId/41/p/1
     */
    public final static String BOOK_LIST = URL_API_PRE + BOOK +"listBook/";

    /**
     * 订单-我的订单详情
     * http://115.28.54.56/xingyun/index.php/Api/Book/getInfo/id/1
     * 参数 id：订单ID
     */
    public final static String BOOK_DETAIL = URL_API_PRE + BOOK +"getInfo/";

}
