package com.hdlink.online.model;

/**
 * Created by zhongqiling on 14-11-6.
 */
public class Shop {
    public String name = "";
    public String address = "";
    public String feature = "";
    public int distance = 0;


    public Shop(String _name, String _addr, String _feature, int _distance){
        name = _name;
        address = _addr;
        feature = _feature;
        distance = _distance;
    }
}
