package com.spring.worldwire.companent;

import com.spring.worldwire.enums.LanguageEnum;
import com.spring.worldwire.enums.LevelEnum;
import com.spring.worldwire.model.Communication;
import com.spring.worldwire.model.LanguageLevel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther pg
 * @Date create in 9:58 2018/7/29
 */
@SuppressWarnings("unused")
@Component
public class ListLanguageLevelConverterConfig extends BaseListConverterConfig<LanguageLevel> implements Converter<String,List<LanguageLevel>> {

    @Override
    LanguageLevel structure(String languageCode,String levelCode) {
        LanguageLevel languageLevel = new LanguageLevel();
        languageLevel.setLanguageEnum(LanguageEnum.getNameByCode(Integer.parseInt(languageCode)));
        languageLevel.setLevel(LevelEnum.getNameByCode(Integer.parseInt(levelCode)));
        return languageLevel;
    }


}
