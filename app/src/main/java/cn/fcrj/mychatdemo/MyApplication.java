package cn.fcrj.mychatdemo;

import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.controller.EaseUI;

/**
 * Created by Administrator on 2016/10/15.pm
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    private String username = "";

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        EaseUI.getInstance().init(this, null);

        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
    }


}
