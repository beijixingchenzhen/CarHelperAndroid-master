package com.hdlink.online.model;

import com.hdlink.online.BuildConfig;
import com.hdlink.online.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * Created by zhongqiling on 14-11-10.
 */
public class GlobalConfig {

    private List<String> recomendServices = new ArrayList<String>();
    private JSONArray serviceCategorySource = new JSONArray();
    private List<ServiceCategory> allServices = new ArrayList<ServiceCategory>();
    private Map<String, String> serviceId2NameMap = new HashMap<String, String>();

    public List<String> regions = Arrays.asList("福田", "南山", "罗湖", "龙华", "龙岗", "宝安");
    public List<String> sortItems = Arrays.asList("默认排序", "距离排序", "价格排序");

    static private GlobalConfig instance = new GlobalConfig();
    static public GlobalConfig shared(){
        return instance;
    }

    public GlobalConfig(){
        String serviceDict = "[{'id':'1','name':'洗车'}]";
        try {
            serviceCategorySource = new JSONArray(serviceDict);
        }catch (Exception e){

        }
        recomendServices.add("1");

        initConfig();
    }

    private void initConfig(){
        visitServiceCategory(serviceCategorySource);
    }

    private void visitServiceCategory(JSONArray arr){
        for(int i =0; i < arr.length(); i++){
            JSONObject obj = null;
            try {
                obj = arr.getJSONObject(i);
                assert BuildConfig.DEBUG && (!obj.has("id") || !obj.has("name"));
                allServices.add(new ServiceCategory(obj));
                serviceId2NameMap.put(obj.getString("id"), obj.getString("name"));
                if(obj.has("sub")){
                    visitServiceCategory(obj.getJSONArray("sub"));
                }
            }catch (JSONException e){

            }
        }
    }

    public List<String> getRecomendServiceList(){
        return recomendServices;
    }

    public String getServiceName(String id){
        return serviceId2NameMap.get(id);
    }

    public int getServiceLogo(String serviceId){
        return R.drawable.ic_launcher;
    }

    public List<String> getFirstCatogoryNames(){
        List<String> names = new ArrayList<String>();
        for(int i = 0; i < allServices.size(); i++){
            names.add(allServices.get(i).name);
        }
        return names;
    }

    public List<List<String>> getSecondCategory(){
        List<List<String>> secendNames = new ArrayList<List<String>>();
        for(int i = 0; i < allServices.size(); i++){

            try {
                ServiceCategory category = allServices.get(i);
                List<String> subNames = new ArrayList<String>();
                for(ServiceCategory one : category.sub){
                    subNames.add(one.name);
                }
                secendNames.add(subNames);
            }catch (Exception e){

            }
        }
        return secendNames;
    }
}
