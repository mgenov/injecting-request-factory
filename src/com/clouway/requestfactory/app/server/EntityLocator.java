package com.clouway.requestfactory.app.server;

import com.clouway.requestfactory.app.model.Customer;
import com.google.web.bindery.requestfactory.shared.Locator;


import javax.inject.Inject;


/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class EntityLocator<T> extends Locator<T, Long> {

  private final CustomerService customerService;

  @Inject
  public EntityLocator(CustomerService customerService) {
    this.customerService = customerService;
  }

  @Override
  public T create(Class<? extends T> clazz) {
    try {
      return clazz.newInstance();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public T find(Class<? extends T> clazz, Long id) {
    return (T) customerService.findCustomer(id);
  }

  @Override
  public Class<T> getDomainType() {
    return (Class<T>)Customer.class;
  }

  @Override
  public Long getId(T domainObject) {
    Customer c = (Customer) domainObject;
    return c.getId();
  }

  @Override
  public Class<Long> getIdType() {
    return Long.class;
  }

  @Override
  public Object getVersion(T domainObject) {
    Customer customer = (Customer) domainObject;
    return customer.getVersion();
  }
}
