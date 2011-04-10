package com.clouway.requestfactory.app.client;

import com.clouway.requestfactory.app.shared.CustomerProxy;
import com.clouway.requestfactory.app.shared.ProvidedServiceProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.CompositeEditor;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class CustomerEditor extends Composite implements Editor<CustomerProxy>,
        CompositeEditor<CustomerProxy, ProvidedServiceProxy, ProvidedServiceEditor> {

  private EditorChain<ProvidedServiceProxy, ProvidedServiceEditor> editorChain;

  interface CustomerEditorUiBinder extends UiBinder<HTMLPanel, CustomerEditor> { }

  private static CustomerEditorUiBinder uiBinder = GWT.create(CustomerEditorUiBinder.class);

  @UiField
  @Path("name")
  TextBox nameBox;

  @UiField
  FlowPanel services;

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

  public void setValue(CustomerProxy value) {
     for (ProvidedServiceProxy providedService : value.getServices()) {
      ProvidedServiceEditor serviceEditor = new ProvidedServiceEditor();
      services.add(serviceEditor);

      editorChain.attach(providedService, serviceEditor);
    }
  }

  public void setDelegate(EditorDelegate<CustomerProxy> customerProxyEditorDelegate) {
  }

}