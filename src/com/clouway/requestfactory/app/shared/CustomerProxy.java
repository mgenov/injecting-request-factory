package com.clouway.requestfactory.app.shared;

import com.clouway.requestfactory.app.model.Customer;
import com.clouway.requestfactory.app.server.EntityLocator;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

import java.util.List;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
@ProxyFor(value = Customer.class,locator = EntityLocator.class)
public interface CustomerProxy extends EntityProxy {

  void setId(Long id);

  Long getId();

  void setVersion(Long version);

  Long getVersion();

  void setName(String name);

  String getName();

  List<ProvidedServiceProxy> getServices();

  void setServices(List<ProvidedServiceProxy> services);

  Integer getAge();

  void setAge(Integer age);
}
