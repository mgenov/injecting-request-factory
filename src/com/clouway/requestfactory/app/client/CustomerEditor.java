package com.clouway.requestfactory.app.client;

import com.clouway.requestfactory.app.shared.CustomerProxy;
import com.clouway.requestfactory.app.shared.ProvidedServiceProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.CompositeEditor;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorContext;
import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.EditorVisitor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.editor.client.adapters.OptionalFieldEditor;
import com.google.gwt.editor.ui.client.ValueBoxEditorDecorator;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.web.bindery.requestfactory.gwt.client.impl.RequestFactoryEditorDelegate;

import java.util.List;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class CustomerEditor extends Composite implements Editor<CustomerProxy>,
        CompositeEditor<CustomerProxy, ProvidedServiceProxy, ProvidedServiceEditor> {

  private EditorChain<ProvidedServiceProxy, ProvidedServiceEditor> editorChain;
  private ProvidedServiceCreationFactory providedServiceCreationFactory;
  private CustomerProxy customerProxy;

  interface CustomerEditorUiBinder extends UiBinder<HTMLPanel, CustomerEditor> { }

  private static CustomerEditorUiBinder uiBinder = GWT.create(CustomerEditorUiBinder.class);

  @UiField
  FlowPanel services;

  @UiField
  ValueBoxEditorDecorator<String> name;

  @UiField
  ValueBoxEditorDecorator<Integer> age;

  @UiField
  Button addProvidedServiceButton;


  public CustomerEditor() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public ProvidedServiceEditor createEditorForTraversal() {
    return new ProvidedServiceEditor();
  }

  public String getPathElement(ProvidedServiceEditor subEditor) {
    return null;
  }

  public void setEditorChain(EditorChain<ProvidedServiceProxy, ProvidedServiceEditor> editorChain) {
    this.editorChain = editorChain;
  }

  public void flush() {
  }

  public void onPropertyChange(String... paths) {

  }

  public void setValue(CustomerProxy customerProxy) {
    services.clear();
    this.customerProxy = customerProxy;
    if (customerProxy.getServices() != null) {
      for (ProvidedServiceProxy providedService : customerProxy.getServices()) {
        ProvidedServiceEditor serviceEditor = new ProvidedServiceEditor();
        services.add(serviceEditor);

        editorChain.attach(providedService, serviceEditor);
      }
    }

  }

  public void setProvidedServiceCreationFactory(ProvidedServiceCreationFactory providedServiceCreationFactory) {
    this.providedServiceCreationFactory = providedServiceCreationFactory;
  }

  @UiHandler("addProvidedServiceButton")
  public void onAddNewService(ClickEvent event) {
    ProvidedServiceProxy providedService = providedServiceCreationFactory.createProvidedService();
    customerProxy.getServices().add(providedService);
    setValue(customerProxy);
  }

  public void setDelegate(EditorDelegate<CustomerProxy> customerProxyEditorDelegate) {

  }

}