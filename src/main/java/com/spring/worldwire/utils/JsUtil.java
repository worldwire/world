package com.spring.worldwire.utils;

public class JsUtil {


  public static String webOpenNewWindow(String url){
    StringBuilder sb = new StringBuilder();
    sb.append("<script language=\"javascript\" type=\"text/javascript\">")
        .append("window.location.href='\\'")
        .append(url)
        .append("\"</script> ");

    return sb.toString();
  }
}
