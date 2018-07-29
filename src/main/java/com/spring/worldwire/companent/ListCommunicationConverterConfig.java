package com.spring.worldwire.companent;

import com.spring.worldwire.model.Communication;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Auther pg
 * @Date create in 9:58 2018/7/29
 */
@SuppressWarnings("unused")
@Component
public class ListCommunicationConverterConfig extends BaseListConverterConfig<Communication> implements Converter<String,List<Communication>> {

    @Override
    Communication structure(String communicationName,String communicationValue) {
        Communication communication = new Communication();
        communication.setValue(communicationValue);
        communication.setName(communicationName);
        return communication;
    }


}
