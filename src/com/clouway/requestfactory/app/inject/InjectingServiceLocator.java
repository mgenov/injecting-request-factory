package com.clouway.requestfactory.app.inject;

import com.google.gwt.requestfactory.shared.ServiceLocator;
import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class InjectingServiceLocator implements ServiceLocator {


  private final Injector injector;

  @Inject
  public InjectingServiceLocator(Injector injector) {
    this.injector = injector;
  }

  public Object getInstance(Class<?> clazz) {
    Object serviceInstance = injector.getInstance(clazz);
    return serviceInstance;
  }
}
