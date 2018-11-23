package com.xiaodu.switchipdemo;

import android.content.Context;
import android.content.Intent;

/**
 * @作者： xiaodu
 * @时间： 2018/11/22
 * @描述：重启app
 */
public class RestartAPPTool {
    /**
     * 重启整个APP
     * @param context
     * @param Delayed 延迟多少毫秒
     */
    public  static void restartAPP(Context context, long Delayed){

        /**开启一个新的服务，用来重启本APP*/
        Intent intent=new Intent(context,KillSelfService.class);
        intent.putExtra("PackageName",context.getPackageName());
        intent.putExtra("Delayed",Delayed);
        context.startService(intent);

        /**杀死整个进程**/
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /***重启整个APP*/
    public static void restartAPP(Context context){
        restartAPP(context,500);//我们传入500毫秒
    }
}
