package com.xianju.test;

import java.util.HashMap;
import java.util.Map;

public class testMapData {
    public static void main(String[] args) {
        System.out.println(data().get("uid"));
    }

    public static Map<String,Object> data(){
        HashMap<String, Object> data = new HashMap<>();
        data.put("uid",2332);
        return data;
    }

}
