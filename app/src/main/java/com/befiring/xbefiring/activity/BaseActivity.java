package com.befiring.xbefiring.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.befiring.xbefiring.Utils.T;

/**
 * Created by Administrator on 2016/7/4.
 */
public class BaseActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public  void showToast(String content){
        T.showToast(this,content, Toast.LENGTH_SHORT);
    }
}
