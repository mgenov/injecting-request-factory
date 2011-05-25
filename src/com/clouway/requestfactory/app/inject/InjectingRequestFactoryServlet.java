package com.clouway.requestfactory.app.inject;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.web.bindery.requestfactory.server.DefaultExceptionHandler;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;

/**
 * An implementation of the RequestFactoryServlet that is using Guice to inject the {@link InjectingServiceLayerDecorator}, so
 * it lets the {@link InjectingServiceLayerDecorator} to control the request processing.
 * <p/>
 * This servlet is bound in the {@link RequestFactoryInjectingModule} and could be injected
 *
 * @see RequestFactoryInjectingModule
 *
 * @author Miroslav Genov (mgenov@gmail.com)
 */
@Singleton
class InjectingRequestFactoryServlet extends RequestFactoryServlet {

  /**
   * Plugs the injectable decorator @{link InjectingServiceLayerDecorator} and the {@link DefaultExceptionHandler}, so the dependencies could be injected by the DI framework.
   */
  @Inject
  public InjectingRequestFactoryServlet(InjectingServiceLayerDecorator serviceLayerDecorator) {
    super(new DefaultExceptionHandler(), serviceLayerDecorator);
  }


}
