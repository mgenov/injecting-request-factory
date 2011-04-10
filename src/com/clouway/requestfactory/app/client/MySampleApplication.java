package com.clouway.requestfactory.app.client;

import com.clouway.requestfactory.app.shared.CustomerProxy;
import com.clouway.requestfactory.app.shared.CustomerRequestFactory;
import com.clouway.requestfactory.app.shared.CustomerRequestFactory.CustomerRequest;
import com.clouway.requestfactory.app.shared.ProvidedServiceProxy;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MySampleApplication implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    VerticalPanel container = new VerticalPanel();
    final EventBus eventBus = new SimpleEventBus();


    final CustomerRequestFactory requestFactory = GWT.create(CustomerRequestFactory.class);
    requestFactory.initialize(eventBus);

    CustomerRequest customerRequest = requestFactory.customerRequest();

    CustomerEditorWorkflow customerEditorWorkflow = new CustomerEditorWorkflow(requestFactory,customerRequest);
    CustomerProxy p = customerRequest.create(CustomerProxy.class);
    p.setName("Test");
    List<ProvidedServiceProxy> services = new ArrayList<ProvidedServiceProxy>();
    for (int i = 0;i < 5;i++) {
      ProvidedServiceProxy providedService = customerRequest.create(ProvidedServiceProxy.class);
      providedService.setName("service:" + (i+1));
      providedService.setPrice(20d);
      services.add(providedService);
    }
    p.setServices(services);
    customerEditorWorkflow.edit(p);
    container.add(customerEditorWorkflow);

    RootPanel.get("container").add(container);

    //test
  }

}
