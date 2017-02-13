package com.nexuslink.config;

import okhttp3.MediaType;

/**
 * Created by 猿人 on 2017/1/14.
 */

public class Constants {

    //===============================================计步器需要用到的常量
    public static final int MSG_FROM_CLIENT =0;
    public static final int MSG_FROM_SERVICE =1;
    public static final int REQUEST_SERVER = 2;
    //TAG
    public static final String TAG = "TAG";
    //服务器请求访问地址
    public static final String BASE_URL = "http://120.77.87.78:8080/arti-sports/api/";
    //图片请求访问地址
    public static final String PHOTO_BASE_URL = "http://120.77.87.78:8080/arti-sports/image/";
    //天气请求的地址
    public static final String WEATHER_BASE_URL="http://op.juhe.cn/";
    //===============================================viewpager image显示常量
    public static final String IMAGE_URL = "image_url";
    public static final String IMAGE_POS = "position";
    //===============================================Tba状态改变
    public static final int VISIBLE = 1;
    public static final int INVISIBLE = 0;
    //===============================================网络请求flag
    public static final int SUCCESS = 200;
    public static final int FAILED = 500;
    //===============================================媒体类型
    public static final MediaType MULTIPART_FORM_DATA = MediaType.parse("multipart/form-data");

}

