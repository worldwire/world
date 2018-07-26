package com.spring.worldwire.converter;

import com.spring.worldwire.enums.RequestTypeEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.util.Objects;

/**
 * Desc: Desc
 * User: luxun
 * Date: 2018/7/26 19:28
 * To change this template use File | Settings | File and Code Templates | Includes | File Header.
 */
@Configuration
public class StringToGenderConverter implements Converter<Integer, RequestTypeEnum> {
    @Override
    public RequestTypeEnum convert(Integer integer) {
        if (Objects.isNull(integer)) {
            return null;
        }
        return RequestTypeEnum.getNameByCode(integer);
    }
}
