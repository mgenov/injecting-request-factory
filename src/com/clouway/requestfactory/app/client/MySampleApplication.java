package com.clouway.requestfactory.app.client;

import com.clouway.requestfactory.app.shared.CustomerRequestFactory;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

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

    CustomerEditorWorkflow customerEditorWorkflow = new CustomerEditorWorkflow(requestFactory);
    customerEditorWorkflow.loadCustomer(1l);
    container.add(customerEditorWorkflow);

    RootPanel.get("container").add(container);

    //test
  }

}
