package com.befiring.xbefiring.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/6/15.
 */
public class T {

    private static T t=null;
    private static Context mContext;


    public static T getT(Context context){
        if(t==null){
            mContext=context;
            t=new T();
        }
        return t;
    }

    public  void show(String content){
        Toast.makeText(mContext,content,Toast.LENGTH_SHORT).show();
    }

}
