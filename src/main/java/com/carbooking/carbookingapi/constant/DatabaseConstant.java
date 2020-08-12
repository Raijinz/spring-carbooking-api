package com.carbooking.carbookingapi.constant;

public class DatabaseConstant {

  private DatabaseConstant() {
      throw new IllegalStateException();
  }

  public static final String AND = " and ";
  public static final String EQUAL_QUESTION_MARK = " = ? ";
  public static final String LIKE_QUESTION_MARK = " like ? ";
  public static final String SIGN_QESTION_MARK = " ? ";
  public static final String SIGN_COMMA = ",";
  public static final String SIGN_PERCENT = "%";
  public static final String EQUAL_OR_MORE_THAN = " >= ";
  
  public static final String ORDER_BY = " ORDER BY ";
  public static final String ASC = "  ASC ";
  public static final String DESC = "  DESC ";

  public static final String WHERE_0_EQUAL_0 = " where 0 = 0 ";
  public static final String FROM = " from ";
  public static final String AND_L = " and l.";
  public static final String OR_U = " or u.";
  public static final String AS_DATE_BACKSLASH_N = " AS DATE) \n";
  public static final String AND_UPPER_PARENTHESES = " and upper(";
  public static final String UPPER_QUESTION_MARK = " = upper(?) ";
  public static final String QUESTION_MARK_EQUAL_CAST_PARENTHESES = " ? =CAST(";
  public static final String AS_DATE_PARENTHESES = " as DATE) ";
  public static final String AND_PARENTHESES = " and (";
}