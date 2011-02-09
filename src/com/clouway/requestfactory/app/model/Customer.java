package com.clouway.requestfactory.app.model;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class Customer {
  private Long id;
  private String name;

  @SuppressWarnings("unused")
  Customer() {
  }

  public Customer(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
