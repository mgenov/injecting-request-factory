package com.clouway.requestfactory.app.model;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class Address {
  private String city;
  private String street;

  @SuppressWarnings("unused")
  Address() {
  }

  public Address(String city, String street) {
    this.city = city;
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public String getStreet() {
    return street;
  }
}
