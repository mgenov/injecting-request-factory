package com.clouway.requestfactory.app.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class Customer {
  private Long id;
  private String name;

  private List<ProvidedService> services = new ArrayList<ProvidedService>();

  @SuppressWarnings("unused")
  public Customer() {
  }

  public Customer(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public List<ProvidedService> getServices() {
    return services;
  }

  public void setServices(List<ProvidedService> services) {
    this.services = services;
  }
}
