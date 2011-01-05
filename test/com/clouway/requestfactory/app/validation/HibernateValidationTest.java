package com.clouway.requestfactory.app.validation;

import com.clouway.requestfactory.app.inject.RequestFactoryInjectingModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class HibernateValidationTest {

  private Validator validator;

  static class Person {

    @NotNull
    @CheckCase(CaseMode.UPPER)
    private String first;

    @NotNull
    @CheckCase(CaseMode.LOWER)
    private String last;

    public Person(String first, String last) {
      this.first = first;
      this.last = last;
    }

    Person() {
    }
  }


  @Before
  public void initializeValidator() {

    Injector injector = Guice.createInjector(new AbstractModule() {
      @Override
      protected void configure() {
        install(new RequestFactoryInjectingModule("/gwtRequest"));
      }
    });

    validator = injector.getInstance(Validator.class);
  }

  @Test
  public void caseAndNotNullValidatorsAreBeingInjected() {
    Person p = new Person();
    Set<ConstraintViolation<Person>> errors = validator.validate(p);

    assertThat(errors.size(), equalTo(2));
  }

  @Test
  public void caseValidationIsPerformedOverFirstAndLastName() {
    Person valid = new Person("TEST", "test");
    Person invalid = new Person("TESttt", "testTTTTT");

    assertThat(validator.validate(valid).size(), equalTo(0));
    assertThat(validator.validate(invalid).size(), equalTo(2));
  }
}
