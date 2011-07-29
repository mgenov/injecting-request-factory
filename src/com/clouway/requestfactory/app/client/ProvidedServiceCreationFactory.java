package com.clouway.requestfactory.app.client;

import com.clouway.requestfactory.app.shared.ProvidedServiceProxy;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public interface ProvidedServiceCreationFactory {

  public ProvidedServiceProxy createProvidedService();

}
