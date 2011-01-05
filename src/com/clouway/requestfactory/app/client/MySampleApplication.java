package com.clouway.requestfactory.app.client;

import com.clouway.requestfactory.app.shared.UserRequestFactory;
import com.clouway.requestfactory.app.shared.UserProxy;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class MySampleApplication implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    VerticalPanel container = new VerticalPanel();

    final Button button = new Button("Get User !");
    final Label label = new Label("");

    final EventBus eventBus = new SimpleEventBus();
    final UserRequestFactory factory = GWT.create(UserRequestFactory.class);
    factory.initialize(eventBus);


    final UserRequestFactory.UserRequest userRequest = factory.userRequest();

    button.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {

        userRequest.getRandomUser().fire(new Receiver<UserProxy>() {

          @Override
          public void onSuccess(UserProxy response) {
            String email = response.getEmail();
            String password = response.getPassword();
            label.setText(email + ", " + password);
          }
        });
      }
    });


    container.add(button);
    container.add(new HTML("<br/><br/>"));
    container.add(label);

    RootPanel.get("container").add(container);
  }

}
