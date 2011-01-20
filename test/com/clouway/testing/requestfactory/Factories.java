package com.clouway.testing.requestfactory;

import com.google.gwt.requestfactory.shared.RequestFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Factories annotation is used to specify which RequestFactory to be injected by the {@link RequestFactoryJreTest}.
 *
 * {@link RequestFactoryJreTest}
 * @author Miroslav Genov (mgenov@gmail.com)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Factories {

  Class<? extends RequestFactory>[] value();

}
