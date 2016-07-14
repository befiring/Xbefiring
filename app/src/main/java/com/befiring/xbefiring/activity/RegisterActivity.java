package com.befiring.xbefiring.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.befiring.xbefiring.R;
import com.befiring.xbefiring.bean.User;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/7/14.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG="RegisterActivity";
    private EditText registerName;
    private EditText registerCode;
    private EditText registerPass;
    private Button btnRegister;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView(){
        registerName=(EditText)findViewById(R.id.et_register_name);
        registerCode= (EditText) findViewById(R.id.et_register_code);
        registerPass= (EditText) findViewById(R.id.et_register_password);
        btnRegister= (Button) findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                register(getUser());
                break;
        }
    }


    public User getUser(){
        User user = new User();
        user.setUsername(registerName.getText().toString());
        user.setPassword(registerPass.getText().toString());
        return user;
    }

    public void register(User user) {
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(e==null){
                    showToast("register success");
                    jumpToLogin();
                }else {
                    Log.i(TAG,e.getMessage()+e.getErrorCode());
                }
            }
        });
    }

    private void jumpToLogin() {
        finish();
        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}
