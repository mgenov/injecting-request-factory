package com.clouway.requestfactory.app.client;

import com.clouway.requestfactory.app.shared.CustomerProxy;
import com.clouway.requestfactory.app.shared.CustomerRequestFactory;
import com.clouway.requestfactory.app.shared.CustomerRequestFactory.CustomerRequest;
import com.clouway.requestfactory.app.shared.ProvidedServiceProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorContext;
import com.google.gwt.editor.client.EditorVisitor;
import com.google.gwt.editor.client.impl.BaseEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.gwt.client.HasRequestContext;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.gwt.client.impl.RequestFactoryEditorDelegate;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.google.web.bindery.requestfactory.shared.Violation;
import sun.beans.editors.DoubleEditor;

import javax.validation.ConstraintViolation;
import java.util.List;
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
  public CustomerProxy currentCustomer;

  public CustomerEditorWorkflow(CustomerRequestFactory requestFactory) {
    this.requestFactory = requestFactory;

    initWidget(ourUiBinder.createAndBindUi(this));
  }

  public void loadCustomer(Long customerId) {

    editorDriver = GWT.create(Driver.class);
    editorDriver.initialize(requestFactory, customerEditor);

    requestFactory.customerRequest().findCustomer(customerId).with("services").to(new Receiver<CustomerProxy>() {

      @Override
      public void onSuccess(CustomerProxy entity) {

        editCustomer(entity);


      }
    }).fire();

  }

  private void editCustomer(CustomerProxy entity) {
    CustomerRequest requestContext = requestFactory.customerRequest();

    entity = requestContext.edit(entity);

    editorDriver.edit(entity, requestContext);

    currentCustomer = entity;

    requestContext.store(currentCustomer).with(editorDriver.getPaths()).to(new Receiver<CustomerProxy>() {
      @Override
      public void onSuccess(CustomerProxy response) {

        editCustomer(response);
      }

      @Override
      public void onFailure(ServerFailure error) {

      }

      @Override
      public void onConstraintViolation(Set<ConstraintViolation<?>> violations) {
        editorDriver.setConstraintViolations(violations);
      }
    });
  }

  @UiHandler("preview")
  public void onPreview(ClickEvent clickEvent) {
    RequestContext requestContext = editorDriver.flush();

    if (editorDriver.hasErrors()) {
      Window.alert("Errors detected locally");
      return;
    }

    requestContext.fire();
  }
}