package com.spring.worldwire.model;

import com.spring.worldwire.enums.LanguageEnum;
import com.spring.worldwire.enums.LevelEnum;

public class LanguageLevel {
  private LanguageEnum languageEnum;
  private LevelEnum level;

  public LanguageEnum getLanguageEnum() {
    return languageEnum;
  }

  public void setLanguageEnum(LanguageEnum languageEnum) {
    this.languageEnum = languageEnum;
  }

  public LevelEnum getLevel() {
    return level;
  }

  public void setLevel(LevelEnum level) {
    this.level = level;
  }
}
