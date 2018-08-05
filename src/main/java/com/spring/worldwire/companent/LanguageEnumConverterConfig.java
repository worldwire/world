package com.spring.worldwire.companent;

import com.spring.worldwire.enums.LanguageEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @Auther pg
 * @Date create in 9:58 2018/7/29
 */
@SuppressWarnings("unused")
@Component
public class LanguageEnumConverterConfig implements Converter<Integer,LanguageEnum> {


    @Override
    public LanguageEnum convert(Integer integer) {
        return LanguageEnum.getNameByCode(integer);
    }
}
