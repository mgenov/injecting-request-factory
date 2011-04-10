package com.clouway.requestfactory.app.client;

import com.clouway.requestfactory.app.shared.CustomerProxy;
import com.clouway.requestfactory.app.shared.CustomerRequestFactory;
import com.clouway.requestfactory.app.shared.CustomerRequestFactory.CustomerRequest;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.requestfactory.client.RequestFactoryEditorDriver;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.ServerFailure;
import com.google.gwt.requestfactory.shared.Violation;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

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
  private final CustomerRequest requestContext;

  public CustomerEditorWorkflow(CustomerRequestFactory requestFactory, CustomerRequest requestContext) {
    this.requestFactory = requestFactory;
    this.requestContext = requestContext;

    initWidget(ourUiBinder.createAndBindUi(this));
  }


  public void edit(CustomerProxy customer) {
    editorDriver = GWT.create(Driver.class);
    editorDriver.initialize(requestFactory, customerEditor);
    editorDriver.edit(customer, requestContext);
  }

  @UiHandler("preview")
  public void onPreview(ClickEvent clickEvent) {

    CustomerRequest context = (CustomerRequest) editorDriver.flush();

    if (editorDriver.hasErrors()) {
      Window.alert("Errors detected locally");
      return;
    }

    context.fire(new Receiver<Void>() {
      @Override
      public void onSuccess(Void response) {
        Window.alert("Sent !");
      }

      @Override
      public void onViolation(Set<Violation> errors) {
        super.onViolation(errors);
      }

      @Override
      public void onFailure(ServerFailure error) {
        super.onFailure(error);
      }
    });
  }
}