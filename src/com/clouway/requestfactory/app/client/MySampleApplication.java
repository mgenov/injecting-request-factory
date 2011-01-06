package com.clouway.requestfactory.app.client;

import com.clouway.requestfactory.app.shared.UserProxy;
import com.clouway.requestfactory.app.shared.UserRequestFactory;
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

    button.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        label.setText("");

        final UserRequestFactory.UserRequest userRequest = factory.userRequest();
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

    Button persist = new Button("Persist Test User");
    persist.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        final UserRequestFactory.UserRequest userRequest = factory.userRequest();
        UserProxy user = userRequest.create(UserProxy.class);
        user.setEmail("test@test.com");
        user.setPassword("tesstttt");

        userRequest.persist(user).fire(new Receiver<Void>() {
          @Override
          public void onSuccess(Void response) {
            label.setText("user persisted");
          }
        });
      }
    });

    container.add(button);
    container.add(new HTML("<br/><br/>"));
    container.add(persist);
    container.add(new HTML("<br/><br/>"));
    container.add(label);

    RootPanel.get("container").add(container);
  }

}
