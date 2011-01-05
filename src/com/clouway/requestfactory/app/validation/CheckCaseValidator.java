package com.clouway.requestfactory.app.validation;

import com.google.inject.Inject;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
class CheckCaseValidator implements ConstraintValidator<CheckCase, String> {

  private final Case ca;
  private CheckCase constraint;

  /**
   * Hooray we are using DI inject the helper class.
   *
   * @param ca the
   */
  @Inject
  public CheckCaseValidator(Case ca) {
    this.ca = ca;
  }

  public void initialize(CheckCase constraint) {
    this.constraint = constraint;
  }

  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (constraint.value() == CaseMode.UPPER) {
      return ca.isUpper(value);
    } else {
      return ca.isLower(value);
    }

  }
}
