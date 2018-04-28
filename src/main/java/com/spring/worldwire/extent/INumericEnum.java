/**      
 * @author xusong  
 * @CreateDate 2017年6月8日 上午10:58:38 
 */
package com.spring.worldwire.extent;

import java.io.Serializable;

/**
 * <p>
 * 实现可转为数字化的枚举接口
 * </p>
 * @author pg
 */
public interface INumericEnum extends Serializable {
  public int getNumericValue();
}
