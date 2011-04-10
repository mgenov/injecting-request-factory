package com.clouway.requestfactory.app.shared;

import com.clouway.requestfactory.app.model.Customer;
import com.clouway.requestfactory.app.server.EntityLocator;
import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyFor;

import java.util.List;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
@ProxyFor(value = Customer.class,locator = EntityLocator.class)
public interface CustomerProxy extends EntityProxy {

  Long getId();

  void setName(String name);

  String getName();

  List<ProvidedServiceProxy> getServices();

  void setServices(List<ProvidedServiceProxy> services);
}
