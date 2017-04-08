package cn.fcrj.myapplication.global;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.jude.swipbackhelper.SwipeListener;

import org.nutz.lang.Strings;

import butterknife.ButterKnife;
import cn.fcrj.myapplication.R;

/**
 * Created by zht on 2017/04/07 12:23
 */

public class BaseToolBarActivity extends BaseActivity {

    private ViewGroup container;
    private Toolbar toolbar;
    private ViewGroup frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SwipeBackHelper.onCreate(this);
        SwipeBackHelper.getCurrentPage(this)//获取当前页面
                .setSwipeBackEnable(true)//设置是否可滑动
                .setSwipeEdge(200)//可滑动的范围。px。200表示为左边200px的屏幕
                .setSwipeEdgePercent(0.2f)//可滑动的范围。百分比。0.2表示为左边20%的屏幕
                .setSwipeSensitivity(0.5f)//对横向滑动手势的敏感程度。0为迟钝 1为敏感
                .setScrimColor(Color.BLACK)//底层阴影颜色
                .setClosePercent(0.6f)//触发关闭Activity百分比
                .setSwipeRelateEnable(true)//是否与下一级activity联动(微信效果)。默认关
                .setSwipeRelateOffset(500)//activity联动时的偏移量。默认500px。
                .setDisallowInterceptTouchEvent(true)//不抢占事件，默认关（事件将先由子View处理再由滑动关闭处理）
                .addListener(new SwipeListener() {//滑动监听

                    @Override
                    public void onScroll(float percent, int px) {//滑动的百分比与距离
                    }

                    @Override
                    public void onEdgeTouch() {//当开始滑动
                    }

                    @Override
                    public void onScrollToClose() {//当滑动关闭
                    }
                });
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }


    @Override
    public void setContentView(int layoutResID) {
        View _iView = getLayoutInflater().inflate(R.layout.base_content, null);
        toolbar = (Toolbar) _iView.findViewById(R.id.base_toolbar);
        container = (ViewGroup) _iView.findViewById(R.id.base__container);
        frame = (ViewGroup) _iView.findViewById(R.id.base__frame);
        initToolbar();
        if (layoutResID > 0) {
            View append = getLayoutInflater().inflate(layoutResID, null);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT);
            getContainer().addView(append, layoutParams);
        }
        super.setContentView(_iView);
        ButterKnife.bind(this);
    }

    protected void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String title = getIntent().getStringExtra(_TITLE);
        if (!Strings.isBlank(title)) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public ViewGroup getContainer() {
        return container;
    }


    public ViewGroup getFrame() {
        return frame;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

}
