package com.clouway.requestfactory.app.shared;

import com.clouway.requestfactory.app.inject.InjectingServiceLocator;
import com.clouway.requestfactory.app.server.CustomerService;
import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;


/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public interface CustomerRequestFactory extends RequestFactory {

  @Service(value = CustomerService.class, locator = InjectingServiceLocator.class)
  interface CustomerRequest extends RequestContext {

    Request<CustomerProxy> store(CustomerProxy customerProxy);

    Request<CustomerProxy> findCustomer(Long id);
  }

  CustomerRequest customerRequest();
}
