package com.clouway.requestfactory.app.model;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class User {
  private Long id;
  private String email;
  private String password;

  public User() {

  }

  public User(String email, String password) {
    this.email = email;
    this.password = password;
    id = 10l;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getVersion() {
    return null;
  }
}
