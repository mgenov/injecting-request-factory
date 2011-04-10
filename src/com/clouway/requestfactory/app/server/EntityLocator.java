package com.clouway.requestfactory.app.server;

import com.google.gwt.requestfactory.shared.Locator;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class EntityLocator<T> extends Locator<T, Long> {

  @Override
  public T create(Class<? extends T> clazz) {
    try {
      return clazz.newInstance();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public T find(Class<? extends T> clazz, Long id) {
    return null;
  }

  @Override
  public Class<T> getDomainType() {
    return null;
  }

  @Override
  public Long getId(T domainObject) {
    return null;
  }

  @Override
  public Class<Long> getIdType() {
    return null;
  }

  @Override
  public Object getVersion(T domainObject) {
    return null;
  }
}
