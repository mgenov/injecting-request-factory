package com.clouway.requestfactory.app.shared;

import com.clouway.requestfactory.app.model.User;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;


/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
@ProxyFor(value = User.class)
public interface UserProxy extends ValueProxy {

  String getEmail();

  String getPassword();

  void setEmail(String email);

  void setPassword(String password);

}
