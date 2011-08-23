package com.clouway.requestfactory.app.shared;

import com.clouway.requestfactory.app.server.PreviewResult;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */

@ProxyFor(PreviewResult.class)
public interface PreviewResultProxy extends ValueProxy {

  public CustomerProxy getCustomer();

  public String getHtmlContent();

}
