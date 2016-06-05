package com.befiring.xbefiring.Utils;

import android.util.Log;

import com.befiring.xbefiring.bean.User;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/6/3.
 */
public class HttpUtil {

    static String strUrl="http://192.168.1.102:8080/Xbefiring/servlet/UserServlet";
    static URL url=null;

    public static void upLoad(){

        User user=new User();
        user.setName("befiring1");
        user.setAge(23);
        user.setSex(1);
        Gson gson=new Gson();
        String jsonStr=gson.toJson(user);
        try{
            url=new URL(strUrl);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            conn.setRequestProperty("Charset","UTF-8");

            conn.connect();

            DataOutputStream dop=new DataOutputStream(conn.getOutputStream());
            dop.writeBytes("param="+ URLEncoder.encode(jsonStr,"UTF-8"));
            dop.flush();
            dop.close();

            BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String result="";
            String readLine=null;
            while((readLine=reader.readLine())!=null){
                result+=readLine;
            }
            reader.close();
            conn.disconnect();
            Log.d("wm","reuslt: "+result);

        }catch(Exception e){
           Log.d("wm","error occured");
        }
    }


}
