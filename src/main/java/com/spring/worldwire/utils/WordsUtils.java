package com.spring.worldwire.utils;

import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

public class WordsUtils {

  public static int countWord(String words,String language){
    if (StringUtils.isBlank(words))
      return 0;
    int count = 0;
    if("chinese".equals(language)){
      return words.trim().length();
    }else if("english".equals(language)){

      Scanner s = new Scanner(words).useDelimiter(" |,|\\?|\\.|\\(|\\)");
      while (s.hasNext()) {
        if (StringUtils.isNotBlank(s.next()))
          count++;
      }
    }
    return count;
  }

  public static void main(String[] args) {
    //String s = " Christmas or Christmas Day is a holiday celebrating the birth of Jesus, the central figure of Christianity. It is traditionally celebrated only test";
    String s = "我爱你中国！你真好";
    System.out.println(countWord(s,"chinese"));
  }


}
