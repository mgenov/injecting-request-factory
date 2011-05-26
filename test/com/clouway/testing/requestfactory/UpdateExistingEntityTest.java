package com.clouway.testing.requestfactory;

import com.clouway.requestfactory.app.model.Customer;
import com.clouway.requestfactory.app.model.ProvidedService;
import com.clouway.requestfactory.app.server.CustomerService;
import com.clouway.requestfactory.app.shared.CustomerProxy;
import com.clouway.requestfactory.app.shared.CustomerRequestFactory;
import com.clouway.requestfactory.app.shared.ProvidedServiceProxy;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;
import org.easymock.Capture;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.*;
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
  public void testUpdateCustomer() {
    Capture<Customer> customerCapture = new Capture<Customer>();
    Customer customer1 = new Customer(1l, "customer1", new Date(), 21, 1l);
    customer1.getServices().add(new ProvidedService("testttttt", 20d));
    expect(customerService.findCustomer(1l)).andReturn(customer1).anyTimes();
    customerService.store(capture(customerCapture));
    replay(customerService);

    CustomerRequestFactory.CustomerRequest request = rf.customerRequest();
    request.findCustomer(1l).with("services").to(new Receiver<CustomerProxy>() {
      @Override
      public void onSuccess(CustomerProxy entity) {
        CustomerRequestFactory.CustomerRequest editRequest = rf.customerRequest();

        entity = editRequest.edit(entity);

        entity.setName("test test");
        entity.setVersion(2l);

        List<ProvidedServiceProxy> services = new ArrayList<ProvidedServiceProxy>();

        ProvidedServiceProxy service = editRequest.create(ProvidedServiceProxy.class);
        service.setName("test1");
        service.setPrice(20d);
        services.add(service);
        entity.getServices().add(service);


        editRequest.store(entity).to(new Receiver<CustomerProxy>() {
          @Override
          public void onSuccess(CustomerProxy response) {

          }
        }).fire();
      }
    }).fire();

//    CustomerProxy p = request.create(CustomerProxy.class);
//    p.setId(1l);
//    p.setName("alal");
//    p.setVersion(1l);
//
//    request.store(p).to(new Receiver<Void>() {
//      @Override
//      public void onSuccess(Void response) {
//
//      }
//    }).fire();

    Customer actualCustomer = customerCapture.getValue();
    assertThat("date was erased ?",actualCustomer.getInstallationDate(), is(not(nullValue())));

    verify(customerService);
  }


}
