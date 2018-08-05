package com.spring.worldwire.companent;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spring.worldwire.model.Communication;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @Auther pg
 * @Date create in 9:58 2018/7/29
 */

public abstract class BaseListConverterConfig<T> {

    public List<T> convert(String source) {
        if(source==null||StringUtils.isBlank(source)){
            return null;
        }
        List<T> list = new ArrayList<>();
        JSONArray jsonArray = JSONArray.parseArray(source);
        for(int i =0;i<jsonArray.size();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Set<String> keys = jsonObject.keySet();

            if(keys.isEmpty()){
                continue;
            }
            Iterator<String> iterator = keys.iterator();
            while(iterator.hasNext()){
                String next = iterator.next();
                T t = structure(next,jsonObject.getString(next));
                list.add(t);
            }
        }
        return list;
    }

    abstract  T structure(String key,String value);

}
