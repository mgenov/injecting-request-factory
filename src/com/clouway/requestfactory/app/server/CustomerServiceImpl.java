package com.clouway.requestfactory.app.server;

import com.clouway.requestfactory.app.model.Customer;
import com.clouway.requestfactory.app.model.ProvidedService;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.InstanceRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class CustomerServiceImpl implements CustomerService {

  private Map<Long, Customer> customers = new HashMap<Long, Customer>() {{
    Customer c1 = new Customer(1l, "customer1", new Date(), 21, 1l);
    c1.getServices().add(new ProvidedService("test",20d));
    put(1l, c1);
    put(2l, new Customer(2l, "customer2", new Date(), 21, 1l));
  }};


  public Customer findCustomer(Long id) {
    return customers.get(id);
  }

  public Customer store(Customer customer) {
    System.out.println("Stored customers.");
    return customer;
  }
}
