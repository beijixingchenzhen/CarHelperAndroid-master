package com.hdlink.online.net;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Created by zhongqiling on 14-5-29.
 */
public class NetProtocol {

    static public final int SUCCESS = 0;
    static public final int ARGUMENT_ERROR = -1;
    static public final int DB_ERROR = -2;
    static public final int WRONG_PROTOCAL = -3;


    static public final int NET_EXCEPTION = -20;
    static public final int NO_RESPONSE = -21;
    static public final int NO_LOGIN = -100;




    public int code;
    public int subcode;
    public String msg = "";
    public JSONObject data = null;
    public JSONArray arrayData = null;

    public NetProtocol(int _code){
        code = _code;
    }

    public NetProtocol(int _code, int _subcode, String _msg, JSONObject _data, JSONArray _arrayData) {
        code = _code;
        subcode = _subcode;
        msg = _msg;
        data = _data;
        arrayData = _arrayData;
    }

}
