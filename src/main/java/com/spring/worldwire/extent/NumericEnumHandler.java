package com.spring.worldwire.extent;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * 
 * 实现mybatis 枚举转为数字化
 * 
 * @author pg
 */
@SuppressWarnings("unused")
public class NumericEnumHandler extends BaseTypeHandler<INumericEnum> {

  private Class<INumericEnum> type;

  /**
   * 设置配置文件设置的转换类以及枚举类内容，供其他方法更便捷高效的实现
   * 
   * @param type 配置文件中设置的转换类
   */
  public NumericEnumHandler(Class<INumericEnum> type) {
    if (type == null)
      throw new IllegalArgumentException("Type argument cannot be null");
    this.type = type;
  }

  @Override
  public INumericEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
    // 根据数据库存储类型决定获取类型，本例子中数据库中存放INT类型
    int i = rs.getInt(columnName);
    if (rs.wasNull()) {
      return null;
    } else {
      // 根据数据库中的code值，定位EnumStatus子类
      return getNumericEnum(i);
    }
  }

  @Override
  public INumericEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    // 根据数据库存储类型决定获取类型，本例子中数据库中存放INT类型
    int i = rs.getInt(columnIndex);
    if (rs.wasNull()) {
      return null;
    } else {
      // 根据数据库中的code值，定位EnumStatus子类
      return getNumericEnum(i);
    }
  }

  @Override
  public INumericEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    // 根据数据库存储类型决定获取类型，本例子中数据库中存放INT类型
    int i = cs.getInt(columnIndex);
    if (cs.wasNull()) {
      return null;
    } else {
      // 根据数据库中的code值，定位EnumStatus子类
      return getNumericEnum(i);
    }
  }

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, INumericEnum parameter, JdbcType arg3) throws SQLException {
    ps.setInt(i, parameter.getNumericValue());

  }

  /**
   * 枚举类型转换，由于构造函数获取了枚举的子类enums，让遍历更加高效快捷
   * 
   * @param code 数据库中存储的自定义code属性
   * @return code对应的枚举类
   */
  private INumericEnum getNumericEnum(int code) {
    if (type == null)
      throw new IllegalArgumentException("Type argument cannot be null");
    this.type = type;
    INumericEnum[] enums = type.getEnumConstants();
    if (enums == null)
      throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
    for (INumericEnum numericEnum : enums) {
      if (numericEnum.getNumericValue() == (Integer.valueOf(code))) {
        return numericEnum;
      }
    }
    throw new IllegalArgumentException("未知的枚举类型：" + code + ",请核对" + type.getSimpleName());
  }

}
