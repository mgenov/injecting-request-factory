package com.clouway.requestfactory.app.client;

import com.clouway.requestfactory.app.shared.CustomerProxy;
import com.clouway.requestfactory.app.shared.CustomerRequestFactory;
import com.clouway.requestfactory.app.shared.CustomerRequestFactory.CustomerRequest;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.google.web.bindery.requestfactory.shared.Violation;

import java.util.Set;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class CustomerEditorWorkflow extends Composite {


  interface CustomerEditorWorkflowUiBinder extends UiBinder<Widget, CustomerEditorWorkflow> {
  }

  private static CustomerEditorWorkflowUiBinder ourUiBinder = GWT.create(CustomerEditorWorkflowUiBinder.class);

  interface Driver extends
          RequestFactoryEditorDriver<CustomerProxy, CustomerEditor> {
  }

  private Driver editorDriver;

  @UiField
  CustomerEditor customerEditor;

  @UiField
  Button preview;

  @UiField
  Button save;

  private final CustomerRequestFactory requestFactory;
  private CustomerRequest requestContext;
  private CustomerProxy currentCustomer;

  public CustomerEditorWorkflow(CustomerRequestFactory requestFactory) {
    this.requestFactory = requestFactory;

    initWidget(ourUiBinder.createAndBindUi(this));
  }

  public void loadCustomer(Long customerId) {
    requestFactory.customerRequest().findCustomer(customerId).with("services").to(new Receiver<CustomerProxy>() {


      @Override
      public void onSuccess(CustomerProxy entity) {
        currentCustomer = entity;
        edit(entity);
      }
    }).fire();

  }

  private void edit(CustomerProxy entity) {
    requestContext = requestFactory.customerRequest();

    entity = requestContext.edit(entity);

    editorDriver = GWT.create(Driver.class);
    editorDriver.initialize(requestFactory, customerEditor);
    editorDriver.edit(entity, requestContext);
  }

  @UiHandler("preview")
  public void onPreview(ClickEvent clickEvent) {

    CustomerRequest context = (CustomerRequest) editorDriver.flush();

    context.store(currentCustomer).to(new Receiver<Void>() {
      @Override
      public void onSuccess(Void response) {
        edit(currentCustomer);

      }
    });

    if (editorDriver.hasErrors()) {
      Window.alert("Errors detected locally");
      return;
    }

    context.fire(new Receiver<Void>() {
      @Override
      public void onSuccess(Void response) {

      }

      @Override
      public void onViolation(Set<Violation> errors) {
//        super.onViolation(errors);
        editorDriver.setViolations(errors);
        Window.alert("Violations size: " + errors.size());
      }

      @Override
      public void onFailure(ServerFailure error) {

      }
    });


  }
}