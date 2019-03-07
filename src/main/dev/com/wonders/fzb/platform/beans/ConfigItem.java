package com.wonders.fzb.platform.beans;

import java.io.Serializable;

public class ConfigItem implements Serializable {
  /**
   * 配置标识
   */
  public String  configId;
  /**
   * 配置显示名称
   */
  public String  configName;
  /**
  * 配置类型
  * CHECKBOX, 复选框,参见本类常数TYPE_CHECKBOX
  * TEXT ，文本输入框，参见本类常数TYPE_TEXT
  */
  public String configType;
  /**
   * 配置项值
   * 类型为CHECKBOX 时，值为常数，参见本类常数项： CHECK_TRUE 、CHECK_FALSE
   */
  public String configValue;
  /**
   * 配置类型 CHECKBOX
   */
  public  static final String TYPE_CHECKBOX="CHECKBOX";
  /**
  * 配置类型 TEXT
  */
  public  static final String TYPE_TEXT="TEXT";

  /**
   * 当等于1时常量为CHECK_TRUE,表示配置被选中
   */
  public  static final String CHECK_TRUE="1";
  /**
   * 当等于0时常量为CHECK_FALSE,表示配置未被选中
   */
  public  static final String CHECK_FALSE="0";
}
