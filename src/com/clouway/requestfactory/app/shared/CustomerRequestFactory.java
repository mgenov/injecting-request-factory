package com.clouway.requestfactory.app.shared;

import com.clouway.requestfactory.app.inject.InjectingServiceLocator;
import com.clouway.requestfactory.app.server.CustomerService;
import com.clouway.requestfactory.app.server.CustomerServiceImpl;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.RequestFactory;
import com.google.gwt.requestfactory.shared.Service;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public interface CustomerRequestFactory extends RequestFactory {

  @Service(value = CustomerService.class, locator = InjectingServiceLocator.class)
  interface CustomerRequest extends RequestContext {
     Request<Void> store(CustomerProxy customerProxy);
  }

  CustomerRequest customerRequest();
}
