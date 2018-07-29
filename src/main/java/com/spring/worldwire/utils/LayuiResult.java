package com.spring.worldwire.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @Auther pg
 * @Date create in 20:40 2018/7/29
 * 按照layui格式返回相应的json
 */
public  class LayuiResult {


    /**
     * 逻辑分页
     * @param list
     * @return
     */
    public static String formatResult(List list){

        JSONObject result = new JSONObject();
        result.put("code", 0);
        result.put("msg", "");
        result.put("count", list.size());
        result.put("data", list);
        return result.toJSONString();

    }

}
