package com.clouway.requestfactory.app.inject;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.web.bindery.requestfactory.server.ServiceLayerDecorator;
import com.google.web.bindery.requestfactory.shared.Locator;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * InjectingServiceLayerDecorator is a ServiceLayerDecorator that uses DI to inject
 * service, entities and the JSR 303 validator.
 */
public class InjectingServiceLayerDecorator extends ServiceLayerDecorator {
  /**
   * JSR 303 validator used to validate requested entities.
   * */
  private final Validator validator;
  private final Injector injector;

  /**
   * Creates new InjectableServiceLayer.
   */
  @Inject
  public InjectingServiceLayerDecorator(Validator validator, Injector injector) {
    this.validator = validator;
    this.injector = injector;
  }

  /**
   * Uses Guice to create the instance of the target locator, so the locator implementation could be injected.
   */
  @Override
  public <T extends Locator<?, ?>> T createLocator(Class<T> clazz) {
    return injector.getInstance(clazz);
  }

  @Override
  public Object createServiceInstance(Class<? extends RequestContext> requestContext) {
    Class<?> serviceClazz = resolveServiceClass(requestContext);
    return injector.getInstance(serviceClazz);
  }


  /**
   * Invokes JSR 303 validator on a given domain object.
   *
   * @param domainObject the domain object to be validated
   * @param <T> the type of the entity being validated
   * @return the violations associated with the domain object
   */
  @Override
  public <T> Set<ConstraintViolation<T>> validate(T domainObject) {
    return validator.validate(domainObject);
  }


}
