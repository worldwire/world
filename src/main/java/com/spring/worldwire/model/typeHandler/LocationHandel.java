package com.spring.worldwire.model.typeHandler;

import com.alibaba.fastjson.JSONObject;
import com.spring.worldwire.model.Location;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationHandel extends BaseTypeHandler<Location> {


    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Location location, JdbcType jdbcType) throws SQLException {
        if (location == null) {
            return;
        }
        preparedStatement.setString(i, JSONObject.toJSONString(location));
    }

    @Override
    public Location getNullableResult(ResultSet resultSet, String s) throws SQLException {
        if (StringUtils.isEmpty(s) || StringUtils.isEmpty(resultSet.getString(s))) {
            return null;
        }
        System.out.println(resultSet.getString(s));
        return JSONObject.parseObject(resultSet.getString(s), Location.class);
    }

    @Override
    public Location getNullableResult(ResultSet resultSet, int i) throws SQLException {
        if (StringUtils.isEmpty(resultSet.getString(i))) {
            return null;
        }
        return JSONObject.parseObject(resultSet.getString(i), Location.class);
    }

    @Override
    public Location getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        if (StringUtils.isEmpty(callableStatement.getString(i))) {
            return null;
        }
        return JSONObject.parseObject(callableStatement.getString(i), Location.class);
    }
}
