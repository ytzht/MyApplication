package cn.fcrj.myapplication.ui.act;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.fcrj.myapplication.R;
import cn.fcrj.myapplication.entity.LoginBean;
import cn.fcrj.myapplication.global.BaseActivity;
import cn.fcrj.myapplication.global.BaseEvent;
import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends BaseActivity {


    @BindView(R.id.btn_jump)
    Button btnJump;
    @BindView(R.id.tv_show)
    TextView tvShow;

    PtrClassicFrameLayout ptrFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ptrFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.fragment_ptr_home_ptr_frame);
        StoreHouseHeader header = new StoreHouseHeader(this);
        header.setPadding(0, LocalDisplay.dp2px(20), 0, LocalDisplay.dp2px(20));
        header.initWithString("fuck you");
        ptrFrameLayout.setDurationToCloseHeader(1500);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrameLayout.refreshComplete();
                    }
                }, 1500);
            }
        });

        ptrFrameLayout.setLastUpdateTimeRelateObject(this);


        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();

        Request request = builder.get().url("http://www.baidu.com/").build();

//        Glide.with(this).load("").into(new ImageView(this));


    }


    @OnClick({R.id.btn_jump, R.id.tv_show})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_jump:

                //发送事件，带对象

                startActivity(EventBusActivity.class);
                break;
            case R.id.tv_show:
                break;
        }
    }


    @Override
    public void onEventMainThread(BaseEvent event) {
        super.onEventMainThread(event);

        if (event.getCode() == BaseEvent.LOG_IN_OUT) {
            tvShow.setText(((LoginBean) event.getAssign()).getName());
        }
    }
}
