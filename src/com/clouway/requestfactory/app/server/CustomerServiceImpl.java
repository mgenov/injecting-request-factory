package com.clouway.requestfactory.app.server;

import com.clouway.requestfactory.app.model.Customer;

/**
 *
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class CustomerServiceImpl implements CustomerService {


  public void store(Customer customer) {
    System.out.println("Stored customers.");
  }
}
