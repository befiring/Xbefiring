package com.befiring.xbefiring.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.befiring.xbefiring.R;
import com.befiring.xbefiring.Utils.HttpUtil;
import com.befiring.xbefiring.bean.User;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Bundle bundle=getIntent().getExtras();
        User user=bundle.getParcelable("user");
        Log.d("wm","user.name= "+user.getName()+"\n"+"user.getAge= "+user.getAge()+"\n"+"user.getSex= "+user.getSex());

        new Thread(){
            @Override
            public void run() {
                super.run();
                HttpUtil.upLoad();
            }
        }.start();
    }
}
