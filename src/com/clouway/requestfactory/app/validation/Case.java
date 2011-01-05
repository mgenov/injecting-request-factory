package com.clouway.requestfactory.app.validation;

/**
 * A helper class that is used to verify the case of entered string values.
 *
 * @see CheckCaseValidator#isValid(Object, javax.validation.ConstraintValidatorContext)
 * @see CaseMode
 *
 * @author Miroslav Genov (mgenov@gmail.com)
 */
class Case {
  /**
   * Verifies whether the provided value is in upper case. If the value is in upper case the
   * returned result is true, otherwise the returned result is false.
   */
  public boolean isUpper(String value) {

    if (value == null || value.isEmpty()) {
      return true;
    }
    for (char ch : value.toCharArray()) {
      if (!Character.isUpperCase(ch)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Verifies whether the provided value is in lower case. If the value is in lower case the
   * returned result if true, otherwise the returned result is false.
   */
  public boolean isLower(String value) {

    if (value == null || value.isEmpty()) {
      return true;
    }

    for (char ch : value.toCharArray()) {
      if (!Character.isLowerCase(ch)) {
        return false;
      }
    }

    return true;
  }
}
