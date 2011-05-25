package com.clouway.requestfactory.app.shared;


import com.clouway.requestfactory.app.inject.InjectingServiceLocator;
import com.clouway.requestfactory.app.server.UserService;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;


/**
 * TODO: Write some comments
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public interface UserRequestFactory extends RequestFactory {

  @Service(value = UserService.class, locator = InjectingServiceLocator.class)
  interface UserRequest extends RequestContext {

    Request<UserProxy> getRandomUser();

    Request<Void> persist(UserProxy userProxy);
  }



  UserRequest userRequest();

}
