package com.spring.worldwire.model.typeHandler;

import com.alibaba.fastjson.JSONObject;
import com.spring.worldwire.model.LanguageLevel;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class LanguageLevelHandel extends BaseTypeHandler<List<LanguageLevel>> {


  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i,
      List<LanguageLevel> languageLevels, JdbcType jdbcType) throws SQLException {
    preparedStatement.setString(i,JSONObject.toJSONString(languageLevels));
  }

  @Override
  public List<LanguageLevel> getNullableResult(ResultSet resultSet, String s) throws SQLException {
    return JSONObject.parseArray(resultSet.getString(s), LanguageLevel.class);
  }

  @Override
  public List<LanguageLevel> getNullableResult(ResultSet resultSet, int i) throws SQLException {
    return JSONObject.parseArray(resultSet.getString(i), LanguageLevel.class);
  }

  @Override
  public List<LanguageLevel> getNullableResult(CallableStatement callableStatement, int i)
      throws SQLException {
    return JSONObject.parseArray(callableStatement.getString(i), LanguageLevel.class);
  }
}
