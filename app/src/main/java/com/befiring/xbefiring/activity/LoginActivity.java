package com.befiring.xbefiring.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.befiring.xbefiring.R;
import com.befiring.xbefiring.Utils.HttpUtil;
import com.befiring.xbefiring.bean.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etName;
    private EditText etPassword;
    private Button btnLogin;

    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        Bundle bundle=getIntent().getExtras();
//        User user=bundle.getParcelable("user");
//        Log.d("wm","user.name= "+user.getName()+"\n"+"user.getAge= "+user.getPassword());

        new Thread(){
            @Override
            public void run() {
                super.run();
                HttpUtil.upLoad(new User("wm","123"));
            }
        }.start();
    }

    public void initView(){
        etName=(EditText)findViewById(R.id.et_name);
        etPassword=(EditText)findViewById(R.id.et_password);
        btnLogin=(Button)findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        User user=new User(etName.getText().toString(),etPassword.getText().toString());
                        boolean b=HttpUtil.login(user);
                        Log.d("wm","user:"+user+" login:"+b);
                    }
                }.start();

                break;
        }
    }
}
