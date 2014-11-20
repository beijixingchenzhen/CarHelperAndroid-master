package com.hdlink.online.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongqiling on 14-11-11.
 */
public class ServiceCategory {

    public String id = "";
    public String name = "";
    public List<ServiceCategory> sub = new ArrayList<ServiceCategory>();

    public ServiceCategory(JSONObject data){
        try{
            id = data.getString("id");
            name = data.getString("name");
            if(data.has("sub")){
                JSONArray subObjs = data.getJSONArray("sub");
                for(int i = 0; i < subObjs.length(); i++){
                    sub.add(new ServiceCategory(subObjs.getJSONObject(i)));
                }
            }

        }catch (JSONException e){

        }
    }
}
