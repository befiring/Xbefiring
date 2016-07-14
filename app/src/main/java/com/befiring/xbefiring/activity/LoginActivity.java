package com.befiring.xbefiring.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.befiring.xbefiring.App;
import com.befiring.xbefiring.DemoContext;
import com.befiring.xbefiring.R;
import com.befiring.xbefiring.Utils.HttpUtil;
import com.befiring.xbefiring.Utils.NetUtils;
import com.befiring.xbefiring.Utils.T;
import com.befiring.xbefiring.bean.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    public static final String TAG = "LoginActivity";
    private EditText etName;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnToRegister;
    private String token;

    private LoginHandler mHandler;

    public class LoginHandler extends Handler {
        private WeakReference<LoginActivity> mActivity;

        public LoginHandler(LoginActivity activity) {
            this.mActivity = new WeakReference<LoginActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            LoginActivity activity = mActivity.get();

            switch (msg.what) {
                case R.id.login_success:
                    activity.startWuziqiPanel();
                    break;
                case R.id.login_failed:
                    showToast("用户名或密码不正确");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

    }

    public void initView() {
        etName = (EditText) findViewById(R.id.et_name);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnToRegister= (Button) findViewById(R.id.to_register);
        btnLogin.setOnClickListener(this);
        btnToRegister.setOnClickListener(this);

        mHandler = new LoginHandler(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login(getUser());
                break;
            case R.id.to_register:
                jumpToRegister();
                break;
        }
    }

    private void jumpToRegister() {
        Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

    private User getUser(){
        User user = new User();
        user.setUsername(etName.getText().toString());
        user.setPassword(etPassword.getText().toString());
        return user;
    }


    private void login(User user) {
        user.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(e==null){
                    showToast("login success");
//                    startWuziqiPanel();
//                    getToken();
                    connect("qBtdYvjCVRRL5Jxff2gJe8rjdnoyoi4x0kJGRpExaQ/Ll2bcPHh1bTKZ3YDjLxVWY8sGz4KwkBY=");
                }else {
                    Log.i(TAG,e.getMessage()+e.getErrorCode());
                }
            }
        });
    }

    public void startWuziqiPanel() {
        Intent intent = new Intent(LoginActivity.this, WuziqiActivity.class);
        startActivity(intent);
        finish();
    }
    public void startConversation(){
        Intent intent = new Intent(LoginActivity.this, ConversationActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 获得token
     */
    private void getToken() {

        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {

                String result = NetUtils.sendGetRequest("token");
                return result;
            }

            @Override
            protected void onPostExecute(String result) {

                try {
                    if (result != null) {
                        JSONObject object = new JSONObject(result);
                        JSONObject jobj = object.getJSONObject("result");

                        if (object.getInt("code") == 200) {
                            token = jobj.getString("token");
                            connect(token);

                            SharedPreferences.Editor edit = DemoContext.getInstance().getSharedPreferences().edit();
                            edit.putString("DEMO_TOKEN", token);
                            edit.apply();

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }


    /**
     * 建立与融云服务器的连接
     *
     * @param token
     */
    private void connect(String token) {

        if (getApplicationInfo().packageName.equals(App.getCurProcessName(getApplicationContext()))) {

            /**
             * IMKit SDK调用第二步,建立与服务器的连接
             */
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                 */
                @Override
                public void onTokenIncorrect() {

                    Log.d("LoginActivity", "--onTokenIncorrect");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token
                 */
                @Override
                public void onSuccess(String userid) {

                    Log.d("LoginActivity", "--onSuccess" + userid);
                    startConversation();
//                    startWuziqiPanel();
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 *                  http://www.rongcloud.cn/docs/android.html#常见错误码
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                    Log.d("LoginActivity", "--onError" + errorCode);
                }
            });
        }
    }
}
