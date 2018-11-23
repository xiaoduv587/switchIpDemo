package com.xiaodu.switchipdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kongzue.dialog.listener.OnMenuItemClickListener;
import com.kongzue.dialog.v2.BottomMenu;
import com.kongzue.dialog.v2.DialogSettings;

import java.util.ArrayList;
import java.util.List;


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

        Button button = findViewById(R.id.tv_switchIp);
        //检验sp是否存值
        String str = SPUtils.getInstance("test", this).getString("ip");
        if (!str.equals("")) {
            button.setText(str);
        }

    }

    /**
     * 切换IP地址
     */
    public void switchIP(View view) {
        List<String> list = new ArrayList<>();
        list.add("www.baidu.com");
        list.add("www.google.com");
        list.add("www.qq.com");
        BottomMenu.show(MainActivity.this, list, new OnMenuItemClickListener() {
            @Override
            public void onClick(final String text, int index) {

                //使用sp记录下来
                SPUtils.getInstance("test", MainActivity.this).put("ip", text);

                //延时1秒重启app,让sp能保存值
                new Handler().postAtTime(new Runnable() {
                    @Override
                    public void run() {
                        //TODO("如果你用的是Retrofit2,不重启ip地址切换不会生效")
                        //一秒后重启app
                        RestartAPPTool.restartAPP(MainActivity.this);
                    }
                },1000);



            }
        }, true);

    }

}
