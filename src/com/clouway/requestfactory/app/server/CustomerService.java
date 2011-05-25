package com.clouway.requestfactory.app.server;

import com.clouway.requestfactory.app.model.Customer;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public interface CustomerService {

  Customer findCustomer(Long id);

  void store(Customer customer);

}
