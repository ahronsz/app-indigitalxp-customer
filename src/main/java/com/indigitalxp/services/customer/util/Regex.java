package com.indigitalxp.services.customer.util;

import java.util.regex.Pattern;

public final class Regex {

  private Regex(){
  }

  public static Boolean isDni(String dni) {
    return patternMatches(dni, "[0-9]{8}");
  }

  public static Boolean isEmail(String email) {
    return patternMatches(email, "^(.+)@(\\S+)$");
  }

  public static Boolean patternMatches(String match, String regexPattern) {
    return Pattern.compile(regexPattern)
        .matcher(match)
        .matches();
  }
}
