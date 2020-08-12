package com.carbooking.carbookingapi.util;

import org.springframework.stereotype.Component;

@Component
public class ObjectValidatorUtils {

  public final String APPLICATION_VALUE_TYPE_INTEGER = "Integer";
  public final String APPLICATION_VALUE_TYPE_BOOLEAN = "Boolean";
  
  public boolean validateMandatory(String str) {
    return !(null == str ||
            "".equals(str.trim()) ||
            str.trim().length() == 0 ||
            "null".equalsIgnoreCase(str));
  }

  public boolean validateType(Object input, String type) {
    try {
      String inputString = String.valueOf(input);
      if (APPLICATION_VALUE_TYPE_INTEGER.equalsIgnoreCase(type)) {
        Integer.parseInt(inputString);
      }
      if (APPLICATION_VALUE_TYPE_BOOLEAN.equalsIgnoreCase(type)) {
        return
          "true".equalsIgnoreCase(inputString) ||
          "false".equalsIgnoreCase(inputString);
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }
}