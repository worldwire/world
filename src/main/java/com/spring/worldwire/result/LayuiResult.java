package com.spring.worldwire.result;

import com.alibaba.fastjson.JSONObject;
import com.spring.worldwire.model.TranslationApply;

import java.util.List;

/**
 * @Auther pg
 * @Date create in 20:40 2018/7/29
 * 按照layui格式返回相应的json
 */
public  class LayuiResult {


    /**
     * 逻辑分页
     * @param list 结果集合
     * @return 返回
     */
    public static String formatResult(List list){

        JSONObject result = new JSONObject();
        result.put("code", 0);
        result.put("msg", "");
        result.put("count", list.size());
        result.put("data", list);
        return result.toJSONString();

    }


    /**
     * 错误返回
     * @param msg 错误提示
     * @return 返回值
     */
    public static String errResult(String msg){
        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("msg", msg);
        return result.toJSONString();
    }

    /**
     *
     * @return 结果
     */
    public static String sussceResult(){
        JSONObject result = new JSONObject();
        result.put("code", 0);
        result.put("msg", "");
        return result.toJSONString();
    }

    public static String formatPageResult(List<TranslationApply> list, int count) {
        JSONObject result = new JSONObject();
        JSONObject page = new JSONObject();
        page.put("count",count);
        result.put("page",page);
        result.put("data",list);
        result.put("code", 0);
        result.put("msg", "");
        return result.toJSONString();
    }
}
