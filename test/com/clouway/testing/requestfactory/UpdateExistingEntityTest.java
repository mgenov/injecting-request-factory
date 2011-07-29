package com.clouway.testing.requestfactory;

import com.clouway.requestfactory.app.model.Customer;
import com.clouway.requestfactory.app.model.ProvidedService;
import com.clouway.requestfactory.app.server.CustomerService;
import com.clouway.requestfactory.app.shared.CustomerProxy;
import com.clouway.requestfactory.app.shared.CustomerRequestFactory;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import org.easymock.Capture;
import org.junit.Test;

import java.util.Date;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
@Factories(value = CustomerRequestFactory.class)
public class UpdateExistingEntityTest extends RequestFactoryJreTest {

  @Inject
  private CustomerRequestFactory rf;

  @Inject
  private CustomerService customerService;

  @Test
  public void findCustomerThenUpdateTheNameOfTheCustomer() {
    Customer customer1 = new Customer(1l, "customer1", new Date(), 21, 1l);
    customer1.getServices().add(new ProvidedService("testttttt", 20d));

    Capture<Customer> customerCapture = new Capture<Customer>();
    expect(customerService.findCustomer(1l)).andReturn(customer1).anyTimes();
    expect(customerService.store(capture(customerCapture))).andReturn(customer1);
    replay(customerService);

    CustomerRequestFactory.CustomerRequest request = rf.customerRequest();
    request.findCustomer(1l).with("services").to(new Receiver<CustomerProxy>() {
      @Override
      public void onSuccess(CustomerProxy customer) {
        CustomerRequestFactory.CustomerRequest storeRequest = rf.customerRequest();

        customer = storeRequest.edit(customer);
        customer.setName("changed_name");

        storeRequest.store(customer).to(new Receiver<CustomerProxy>() {
          @Override
          public void onSuccess(CustomerProxy response) {
            assertThat(response.getName(),is(equalTo("changed_name")));
          }
        }).fire();
      }
    }).fire();

    assertThat("customer name was not updated correctly after name was updated?",customerCapture.getValue().getName(),is(equalTo("changed_name")));

    verify(customerService);
  }


}
