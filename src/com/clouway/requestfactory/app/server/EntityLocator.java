package com.clouway.requestfactory.app.server;

import com.clouway.requestfactory.app.model.Customer;
import com.google.gwt.requestfactory.shared.Locator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class EntityLocator<T> extends Locator<T, Long> {

   private Map<Long, Customer> customers = new HashMap<Long, Customer>() {{
    put(1l,new Customer(1l, "customer1", new Date(),1l));
    put(2l,new Customer(2l, "customer2", new Date(),1l));
  }};

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
    return (T) customers.get(id);
  }

  @Override
  public Class<T> getDomainType() {
    return (Class<T>)Customer.class;
  }

  @Override
  public Long getId(T domainObject) {
    for (Customer customer : customers.values()) {
      Customer c = (Customer) domainObject;
      if (c.getId() != null && c.getId().equals(customer.getId())) {
        return c.getId();
      }
    }
    return null;
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
