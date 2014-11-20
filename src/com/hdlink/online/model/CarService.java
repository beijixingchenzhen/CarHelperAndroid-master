package com.hdlink.online.model;

import java.util.List;

/**
 * Created by zhongqiling on 14-11-6.
 */
public class CarService {
    //洗车等类型的Id
    public String typeId = "";
    public String name = "";
    //该服务商店的Id
    public String shopId = "";
    public String shopName = "";
    public String shopAddr = "";
    //与当前所在地距离
    public int distance = -1;
    public int price = -1;
    public int originPrice = -1;
    //特色
    public String feature = "";
    public List<Comment> comments = null;

    public CarService(String _typeId, String _name, int _price, int _distance){
        this.typeId = _typeId;
        this.name = _name;
        this.price = _price;
        this.distance = _distance;
    }

}
