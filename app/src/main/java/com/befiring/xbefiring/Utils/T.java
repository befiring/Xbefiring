package com.befiring.xbefiring.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/6/15.
 */
public class T {

    private static Toast toast = null;
    private static Context mContext;


    public static void showToast(Context context, CharSequence content, int duration) {
        mContext = context;
        if (toast == null) {
            toast = Toast.makeText(context, content, duration);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

}
