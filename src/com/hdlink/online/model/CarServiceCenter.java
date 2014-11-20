package com.hdlink.online.model;

import com.hdlink.online.defines.ServiceFilterType;

import java.util.*;

/**
 * Created by zhongqiling on 14-11-6.
 */
public class CarServiceCenter {

    static public String WHOLE = "whole";

    //客户端暂存得全部服务
    public List<CarService> services = new ArrayList<CarService>();

    private List<String> availableSort = Arrays.asList(ServiceFilterType.SORT_DEFAULT,
                                         ServiceFilterType.SORT_DISTANCE, ServiceFilterType.SORT_PRICE);

    private String typeCond = ServiceFilterType.ALL;
    private String districtCond = ServiceFilterType.ALL;
    private String sortCond = ServiceFilterType.SORT_DEFAULT;

    static private CarServiceCenter instance = new CarServiceCenter();
    static public CarServiceCenter shared(){
        return instance;
    }

    public CarServiceCenter(){
        for(int i = 0; i < 3; i++){
            services.add(new CarService("111", "洗车", 100, 200));
        }
        for(int i = 0; i < 5; i++){
            services.add(new CarService("222", "保养", 120, 250));
        }
        services.add(new CarService("333", "维修", 130, 120));
        services.add(new CarService("333", "维修", 135, 180));
        services.add(new CarService("333", "维修", 160, 400));
    }


    public List<CarService> get(String sortType){
        return get(sortType, null);
    }


    public List<CarService> get(String filterType, String data){
        List<CarService> sortServices = new ArrayList<CarService>();

        if(data == null && availableSort.contains(filterType)){
            sortCond = filterType;
        }
        else if(filterType.equals(ServiceFilterType.CARSERVICE_TYPE)){
            typeCond = data;
        }
        else if(filterType.equals(ServiceFilterType.DISTRICT)){
            districtCond = data;
        }

        return get();
    }

    public List<CarService> get(){
        List<CarService> filterServices = new ArrayList<CarService>();

        for(CarService service : services) {
            if (typeCond != null && !typeCond.equals(ServiceFilterType.ALL) && service.typeId.equals(typeCond))
                filterServices.add(service);
        }

        if(filterServices.size() == 0)
            filterServices = services;

        if(availableSort.contains(sortCond) && !sortCond.equals(ServiceFilterType.SORT_DEFAULT))
            Collections.sort(filterServices, new Comparator<CarService>() {
                @Override
                public int compare(CarService lhs, CarService rhs) {
                    int rValue = 0;
                    int lValue = 0;
                    if(sortCond.equals(ServiceFilterType.SORT_DISTANCE)){
                        rValue = rhs.distance;
                        lValue = lhs.distance;
                    }
                    else if(sortCond.equals(ServiceFilterType.SORT_PRICE)){
                        lValue = lhs.price;
                        rValue = rhs.price;
                    }

                    if(lValue >= 0 && rValue >= 0)
                        return lhs.distance - rhs.distance;
                    else if(lhs.distance >= 0)
                        return -1;
                    else if(rhs.distance >= 0)
                        return 1;
                    else
                        return 0;

                }
            });

        return filterServices;
    }

}
