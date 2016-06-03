package com.befiring.xbefiring.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.befiring.xbefiring.R;
import com.befiring.xbefiring.Utils.HttpUtil;
import com.befiring.xbefiring.bean.User;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Second Activity");

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
