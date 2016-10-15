package cn.fcrj.mychatdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.fcrj.mychatdemo.activity.BaseActivity;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity=====";
    @BindView(R.id.account)
    EditText account;
    @BindView(R.id.psd)
    EditText psd;
    @BindView(R.id.reg)
    Button reg;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.exit)
    Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Glide.with(this);

        text.setText("Hello world");


    }

    @OnClick({R.id.reg, R.id.login, R.id.exit})
    public void onClick(View view) {
        Log.d(TAG, "onClick: " + view.getId());
        switch (view.getId()) {
            case R.id.reg:
                //注册失败会抛出HyphenateException
                try {
                    Log.d(TAG, "onClick: " + account.getText().toString() + "   " + psd.getText().toString());
                    EMClient.getInstance().createAccount(account.getText().toString(), psd.getText().toString());//同步方法
                    text.setText("注册成功，点击登录");
                    SnackMsg(reg, "注册成功，点击登录");
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    SnackMsg(reg, e.toString() + "   " + e.getErrorCode() + "   " + e.getDescription() + "   " + e.getMessage());
                    text.setText(e.toString() + "   " + e.getErrorCode() + "   " + e.getDescription() + "   " + e.getMessage());
                }
                break;
            case R.id.login:
                EMClient.getInstance().chatManager().loadAllConversations();
                EMClient.getInstance().groupManager().loadAllGroups();

                EMClient.getInstance().login(account.getText().toString(), psd.getText().toString(), new EMCallBack() {//回调
                    @Override
                    public void onSuccess() {
                        EMClient.getInstance().groupManager().loadAllGroups();
                        EMClient.getInstance().chatManager().loadAllConversations();
                        SnackMsg(login, "登录聊天服务器成功！");
                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }

                    @Override
                    public void onError(int code, String message) {
                        SnackMsg(login, "登录聊天服务器失败！"+code+message);
                    }
                });

                break;
            case R.id.exit:

//                EMClient.getInstance().logout(true);
                EMClient.getInstance().logout(true, new EMCallBack() {

                    @Override
                    public void onSuccess() {
                        SnackMsg(exit, "退出成功");
                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }

                    @Override
                    public void onError(int code, String message) {
                        SnackMsg(exit, code +"   "+ message);

                    }
                });

                break;
        }
    }

}
