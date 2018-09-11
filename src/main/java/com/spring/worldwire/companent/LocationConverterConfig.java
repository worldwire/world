package com.spring.worldwire.companent;

import com.alibaba.fastjson.JSONObject;
import com.spring.worldwire.model.Location;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @Auther pg
 * @Date create in 9:58 2018/7/29
 */
@SuppressWarnings("unused")
@Component
public class LocationConverterConfig implements Converter<String,Location> {


    @Override
    public Location convert(String s) {
        if(StringUtils.isBlank(s)){
            return null;
        }
        return JSONObject.parseObject(s,Location.class);
    }
}
