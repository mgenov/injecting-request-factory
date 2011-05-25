package com.clouway.testing.requestfactory;

import com.clouway.requestfactory.app.model.Customer;
import com.clouway.requestfactory.app.server.CustomerService;
import com.clouway.requestfactory.app.server.CustomerServiceImpl;
import com.clouway.requestfactory.app.shared.CustomerProxy;
import com.clouway.requestfactory.app.shared.CustomerRequestFactory;
import com.clouway.requestfactory.app.shared.ProvidedServiceProxy;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.inject.Inject;
import org.easymock.Capture;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.ArrayList;
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

    customerService.store(capture(customerCapture));
    replay(customerService);

    CustomerRequestFactory.CustomerRequest request = rf.customerRequest();

    CustomerProxy entity = request.create(CustomerProxy.class);
    entity.setId(1l);
    entity.setName("test");
    entity.setVersion(2l);

    List<ProvidedServiceProxy> services = new ArrayList<ProvidedServiceProxy>();

    ProvidedServiceProxy service1 = request.create(ProvidedServiceProxy.class);
    services.add(service1);

    entity.setServices(services);

    request.store(entity).to(new Receiver<Void>() {
      @Override
      public void onSuccess(Void response) {

      }
    }).fire();

    Customer actualCustomer = customerCapture.getValue();
    assertThat("date was erased ?",actualCustomer.getInstallationDate(), is(not(nullValue())));

    verify(customerService);
  }


}
