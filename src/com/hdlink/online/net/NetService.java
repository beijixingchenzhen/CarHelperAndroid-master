package com.hdlink.online.net;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import com.hdlink.online.R;
import com.hdlink.online.Utils;
import com.hdlink.online.activity.BaseActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zhongqiling on 14-11-5.
 */
public class NetService {

    public interface NetCallBack {
        public void onCall(NetProtocol result);
    }

    private BaseActivity mActivity = null;
    private Context mContext = null;

    public NetService(BaseActivity activity) {
        mActivity = activity;
        mContext = mActivity.getApplicationContext();
    }

    public NetService(Context context) {
        mContext = context;
    }


    public void login(String username, String psw, NetCallBack callBack){
        Map<String, String> parmsMap = new HashMap<String, String>();
        parmsMap.put("phone", username);
        parmsMap.put("password", psw);

        urlRequest(mContext.getString(R.string.server_addr)+"&m=login", createReqParms(parmsMap), "POST", callBack);
    }

    public void quickLogin(NetCallBack callBack){
        urlRequest(mContext.getString(R.string.server_addr)+"&m=checkLogin", createReqParms(null), "POST", callBack);
    }

    private String createReqParms(Map<String, String> parmsMap) {
        String userId = Utils.getGlobalData(mContext, "userId");

        StringBuilder builder = new StringBuilder();
        if (parmsMap != null) {
            for (Map.Entry<String, String> entry : parmsMap.entrySet()) {
                builder.append("&" + entry.getKey() + "=" + entry.getValue() + "&");
            }
//            str = builder.toString();
        }
        return builder.toString();
    }


    public void urlRequest(String url, String parms, String method, final NetCallBack callback) {

        new AsyncTask<String, Integer, NetProtocol>() {
            protected NetProtocol doInBackground(String... args) {

                URL url = null;
                HttpURLConnection conn = null;

                String method = args[2];
                String urlString = method.equals("GET") ? args[0] + "?" + args[1] : args[0];

                try {

                    url = new URL(urlString);

                    conn = (HttpURLConnection) url.openConnection();
                    HttpURLConnection.setFollowRedirects(false);
                    conn.setConnectTimeout(20 * 1000);
                    conn.setReadTimeout(20*1000);
                    conn.setRequestMethod(method);
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    conn.setRequestProperty("Accept-Encoding", "gzip");
//                    conn.connect();

                    String cookieString = args[3];
                    if (cookieString != null) {
                        Log.i("get local cookie:", cookieString);
                        conn.setRequestProperty("Cookie", cookieString);
                    }
                    conn.setDoInput(true);

                    if (!method.equals("GET")) {
                        conn.setDoOutput(true);
                        OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                        out.write(args[1]);

                        out.flush();
                        out.close();
                    }

                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuffer buffer = new StringBuffer();
                    String currline = "";
                    while ((currline = reader.readLine()) != null) {
                        buffer.append(currline);
                    }
                    saveCookie(conn);
                    reader.close();
                    in.close();

                    if (buffer.length() > 0) {

                        JSONObject receiveData = new JSONObject(buffer.toString());
                        JSONObject data = null;
                        JSONArray arrayData = null;
                        if(receiveData.has("data")){
                            String dataString = receiveData.getString("data");

                            try {
                                data = new JSONObject(dataString);
                            } catch (JSONException e) {
                                data = null;
                                try {
                                    arrayData = new JSONArray(dataString);
                                } catch (JSONException e2) {
                                    arrayData = null;
                                }
                            }
                        }

                        if(receiveData.has("code") && receiveData.has("subcode"))
                            return new NetProtocol(receiveData.getInt("code"), receiveData.getInt("subcode"),
                                                    receiveData.getString("msg"), data, arrayData);
                        else
                            return new NetProtocol(NetProtocol.WRONG_PROTOCAL, NetProtocol.WRONG_PROTOCAL, "返回数据格式错误", null, null);

                    } else {
                        return new NetProtocol(NetProtocol.NO_RESPONSE, NetProtocol.NO_RESPONSE, "网速有点慢，稍后再试", null, null);
                    }

                } catch (Exception e) {
                    Log.d("error:", e.toString());
                    return new NetProtocol(NetProtocol.NET_EXCEPTION,  NetProtocol.NET_EXCEPTION, e.toString(), null, null);
                } finally {
                    conn.disconnect();
                }

            }

            protected void onPostExecute(NetProtocol result) {
                if (callback != null) {
                    callback.onCall(result);
                }


            }

        }.execute(url, parms, method, getCookie());
    }

    private String getCookie() {
        SharedPreferences pref = mContext.getSharedPreferences(mContext.getString(R.string.app_name), Activity.MODE_PRIVATE);
        return pref.getString("cookie", null);
    }

    private void saveCookie(HttpURLConnection connection) {
        Map<String, List<String>> headers = connection.getHeaderFields();
        Iterator it = headers.keySet().iterator();

        while (it.hasNext()) {
            Object key = it.next();
            if ("Set-Cookie".equalsIgnoreCase(((String) key))) {
                SharedPreferences pref = mContext.getSharedPreferences(mContext.getString(R.string.app_name), 0);
                String casheCookieString = pref.getString("cookie", "");
                Map<String, String> cookieMap = new HashMap<String, String>();
                if (casheCookieString.length() > 0) {
                    for (String eachCookie : casheCookieString.split(";")) {
                        String[] keyAndValue = eachCookie.split("=");
                        cookieMap.put(keyAndValue[0], keyAndValue[1]);
                    }
                }


                for (String cookieString : headers.get(key)) {
                    List<HttpCookie> cookies = HttpCookie.parse(cookieString);
                    for (HttpCookie cookie : cookies) {
                        if (!cookie.hasExpired()) {
                            cookieMap.put(cookie.getName(), cookie.getValue());
                        }
                    }
                }

                StringBuilder stringBuilder = new StringBuilder();
                Iterator it2 = cookieMap.entrySet().iterator();
                while (it2.hasNext()) {
                    Map.Entry entry = (Map.Entry) it2.next();
                    stringBuilder.append(entry.getKey() + "=" + entry.getValue());
                    stringBuilder.append(";");
                }

                SharedPreferences pref2 = mContext.getSharedPreferences(mContext.getString(R.string.app_name), 0);
                SharedPreferences.Editor editor = pref2.edit();
                editor.putString("cookie", stringBuilder.toString());
                editor.commit();
                Log.i("---get cookie:", stringBuilder.toString());
            }
        }

    }

}
