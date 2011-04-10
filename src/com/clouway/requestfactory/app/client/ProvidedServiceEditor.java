package com.clouway.requestfactory.app.client;

import com.clouway.requestfactory.app.shared.ProvidedServiceProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class ProvidedServiceEditor extends Composite implements Editor<ProvidedServiceProxy> {
  interface ProvidedServiceEditItemUiBinder extends UiBinder<HTMLPanel, ProvidedServiceEditor> { }

  private static ProvidedServiceEditItemUiBinder uiBinder = GWT.create(ProvidedServiceEditItemUiBinder.class);

  @UiField
  TextBox name;

  public ProvidedServiceEditor() {
    initWidget(uiBinder.createAndBindUi(this));
  }
}