package com.clouway.requestfactory.app.server;

import com.clouway.requestfactory.app.inject.RequestFactoryInjectingModule;
import com.clouway.requestfactory.app.model.User;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class ApplicationBootloader extends GuiceServletContextListener {

  @Override
  protected Injector getInjector() {

    Injector injector = Guice.createInjector(new AbstractModule() {

      @Override
      protected void configure() {
        install(new RequestFactoryInjectingModule("/gwtRequest"));

        bind(User.class).toInstance(new User("test@test.com", "1234567"));
      }
    });

    return injector;
  }
}
