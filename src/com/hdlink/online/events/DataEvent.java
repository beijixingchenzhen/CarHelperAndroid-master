package com.hdlink.online.events;


/**
 * Created by zhongqiling on 14-6-20.
 */
public class DataEvent {

    public String type = "";
    public Object data = null;

    public DataEvent(String eventType){
        type = eventType;
    }

    public DataEvent(String eventType, Object _data) {
        type = eventType;
        data = _data;
    }

    public void setData(Object _data) {
        data = _data;
    }
}
