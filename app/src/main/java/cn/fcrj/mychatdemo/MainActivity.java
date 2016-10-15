package cn.fcrj.mychatdemo;

import android.os.Bundle;

import cn.fcrj.mychatdemo.activity.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
