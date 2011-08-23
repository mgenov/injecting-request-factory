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

    put(c1.getId(), c1);
  }};


  public Customer findCustomer(Long id) {
    if (new Long(1l).equals(id)) {
      Customer c1 = new Customer(1l, "customer1", new Date(), 21, 1l);
      c1.getServices().add(new ProvidedService("test", 20d));
      return c1;
    }
    return customers.get(id);
  }

  public Customer store(Customer customer) {
    if (customer.getId() == null) {
      customer.setId(new Long(customers.size() + 1));
    }
    System.out.println("Customer name: " + customer.getName());
    customers.put(customer.getId(), customer);
    return customer;
  }

  public PreviewResult previewCustomer(Customer customer) {
    return new PreviewResult(customer, "The new name of the customer: " + customer.getName());
  }
}
