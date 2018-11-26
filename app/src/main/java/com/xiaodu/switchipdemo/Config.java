package com.xiaodu.switchipdemo;

/**
 * @作者： xiaodu
 * @时间： 2018/11/26
 * @描述：
 */
public class Config {

    public static final String IP="ip";
    public static String baseUrl="http://c.test.com";


    public static String getBaseUrl(){
        if (!SPUtils.getInstance("test", App.sContext).getString(IP).equals("")) {
            return SPUtils.getInstance("test", App.sContext).getString(IP);
        }
        return baseUrl;
    }

}
