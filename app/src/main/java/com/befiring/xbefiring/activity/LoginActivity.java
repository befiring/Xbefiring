package com.befiring.xbefiring.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.befiring.xbefiring.R;
import com.befiring.xbefiring.Utils.HttpUtil;
import com.befiring.xbefiring.Utils.T;
import com.befiring.xbefiring.bean.User;

import java.lang.ref.WeakReference;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    public static final String TAG="LoginActivity";
    private EditText etName;
    private EditText etPassword;
    private Button btnLogin;

    private LoginHandler mHandler;
    private static T t;
    int i=1;

    public class LoginHandler extends Handler{
        private WeakReference<LoginActivity>  mActivity;

        public LoginHandler(LoginActivity activity){
            this.mActivity=new WeakReference<LoginActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            LoginActivity activity=mActivity.get();

            switch (msg.what){
                case R.id.login_success:
                    activity.startWuziqiPanel();
                    break;
                case R.id.login_failed:
//                    t.show("用户名或密码不正确");
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
        btnLogin.setOnClickListener(this);

        mHandler=new LoginHandler(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                User user = new User(etName.getText().toString(), etPassword.getText().toString());
//                login(user);
                showToast(i+++"");
                break;
        }
    }

    public void login(User user) {
        new AsyncTask<User, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(User... params) {
                return HttpUtil.login(params[0]);
            }
            @Override
            protected void onPostExecute(Boolean result) {
                Log.d(TAG,"login result:"+result);
                if(result){
                    mHandler.sendEmptyMessage(R.id.login_success);
                }else{
                    mHandler.sendEmptyMessage(R.id.login_failed);
                }

            }
        }.execute(user);
    }

    public void startWuziqiPanel(){
        Intent intent=new Intent(LoginActivity.this,WuziqiActivity.class);
        startActivity(intent);
    }

}
