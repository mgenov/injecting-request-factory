package com.clouway.requestfactory.app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class Customer {
  private Long id;
  private String name;
  private Date installationDate;
  private Long version;

  private List<ProvidedService> services = new ArrayList<ProvidedService>();

  @SuppressWarnings("unused")
  public Customer() {
  }

  public Customer(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Customer(Long id, String name, Date installationDate) {
    this.id = id;
    this.name = name;
    this.installationDate = installationDate;
  }

  public Customer(Long id, String name, Date installationDate, Long version) {
    this.id = id;
    this.name = name;
    this.installationDate = installationDate;
    this.version = version;
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

  public Date getInstallationDate() {
    return installationDate;
  }

  public void setInstallationDate(Date installationDate) {
    this.installationDate = installationDate;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public Long getVersion() {
    return version;
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
