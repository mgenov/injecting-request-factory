package com.clouway.requestfactory.app.server;

import com.clouway.requestfactory.app.model.Customer;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class PreviewResult {
  private Customer customer;
  private String htmlContent;


  @SuppressWarnings("unused")
  PreviewResult() {
  }


  public PreviewResult(Customer customer, String htmlContent) {
    this.customer = customer;
    this.htmlContent = htmlContent;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public String getHtmlContent() {
    return htmlContent;
  }

  public void setHtmlContent(String htmlContent) {
    this.htmlContent = htmlContent;
  }
}
