package com.clouway.requestfactory.app.server;

import com.clouway.requestfactory.app.model.Customer;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public interface CustomerService {
  void store(Customer customer);
}
