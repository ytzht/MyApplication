package cn.fcrj.myapplication.ui.act;

import android.os.Bundle;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.fcrj.myapplication.R;
import cn.fcrj.myapplication.entity.LoginBean;
import cn.fcrj.myapplication.global.BaseEvent;
import cn.fcrj.myapplication.global.BaseToolBarActivity;

public class EventBusActivity extends BaseToolBarActivity {

    @BindView(R.id.tv_event)
    TextView tvEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        ButterKnife.bind(this);

        EventBus.getDefault().post(BaseEvent.event(BaseEvent.LOG_IN_OUT, new LoginBean("张三", "aa")));
    }


    @Override
    public void onEventMainThread(BaseEvent event) {
        super.onEventMainThread(event);

        if (event.getCode() == BaseEvent.LOG_IN_OUT) {
            LoginBean bean = (LoginBean) event.getAssign();
            if (bean == null) {
                return;
            }
            tvEvent.setText(bean.getName());

            for (int i = 0; i < 100; i++) {
                showShort(bean.getName() + i);
            }
        }
    }


}
