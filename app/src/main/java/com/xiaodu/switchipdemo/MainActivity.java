package com.xiaodu.switchipdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kongzue.dialog.listener.OnMenuItemClickListener;
import com.kongzue.dialog.v2.BottomMenu;
import com.kongzue.dialog.v2.DialogSettings;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


import static com.kongzue.dialog.v2.DialogSettings.TYPE_MATERIAL;

/**
 * 需要注意的点：
 * 1.ip写成变量
 * 2.记得让app重启，否则不生效
 * 3.记得添加KillSelfService到清单文件
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化Dialog
        DialogSettings.type = TYPE_MATERIAL;

        TextView tv_ip = findViewById(R.id.tv_ip);

        //检验sp是否存值
        String str = SPUtils.getInstance("test", this).getString(Config.IP);
        if (!str.equals("")) {
//            button.setText(str);
            tv_ip.setText(str);
        }


        EasyHttp.get("")
                .timeStamp(true)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(String s) {
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                });

    }

    /**
     * 切换IP地址
     */
    public void switchIP(View view) {

        HashMap<String, String> map = new HashMap<>();
        map.put("开发1", "http://10.129.53.49:8080");
        map.put("开发2", "http://10.129.53.49:8080");
        map.put("测试地址", "http://a.test.com");
        map.put("正式地址", "http://b.test.com");

        Set<String> set = MapUtils.getKeySetByMap(map);

        List<String> keyListBySet = MapUtils.getKeyListBySet(set);

        List<String> keyList = MapUtils.getListByMap(map, true);
        final List<String> valuesList = MapUtils.getListByMap(map, false);


        BottomMenu.show(MainActivity.this, keyList, new OnMenuItemClickListener() {
            @Override
            public void onClick(final String text, int index) {

                //使用sp记录下来
                SPUtils.getInstance("test", MainActivity.this).put(Config.IP, valuesList.get(index));

                //延时1秒重启app,让sp能保存值
                new Handler().postAtTime(new Runnable() {
                    @Override
                    public void run() {
                        //TODO("如果你用的是Retrofit2,不重启ip地址切换不会生效")
                        //一秒后重启app
                        RestartAPPTool.restartAPP(MainActivity.this);
                    }
                }, 1000);


            }
        }, true);

    }

}
