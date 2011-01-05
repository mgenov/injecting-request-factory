package com.clouway.requestfactory.app.shared;

import com.clouway.requestfactory.app.model.User;
import com.google.gwt.requestfactory.shared.ProxyFor;
import com.google.gwt.requestfactory.shared.ValueProxy;


/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
@ProxyFor(value = User.class)
public interface UserProxy extends ValueProxy {

  String getEmail();

  String getPassword();

}
