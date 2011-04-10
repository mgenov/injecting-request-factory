package com.clouway.requestfactory.app.model;

/**
 *
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class ProvidedService {

  private String name;
  private Double price;

  public ProvidedService() {
  }

  public ProvidedService(String name, Double price) {
    this.name = name;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }
}
