package com.spring.worldwire.model.typeHandler;

import com.alibaba.fastjson.JSONObject;
import com.spring.worldwire.model.Communication;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CommunicationHandel extends BaseTypeHandler<List<Communication>> {


  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<Communication> commulications, JdbcType jdbcType) throws SQLException {
    preparedStatement.setString(i,JSONObject.toJSONString(commulications));
  }

  @Override
  public List<Communication> getNullableResult(ResultSet resultSet, String s) throws SQLException {
    return JSONObject.parseArray(resultSet.getString(s),Communication.class);
  }

  @Override
  public List<Communication> getNullableResult(ResultSet resultSet, int i) throws SQLException {
    return JSONObject.parseArray(resultSet.getString(i),Communication.class);
  }

  @Override
  public List<Communication> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
    return JSONObject.parseArray(callableStatement.getString(i),Communication.class);
  }
}
