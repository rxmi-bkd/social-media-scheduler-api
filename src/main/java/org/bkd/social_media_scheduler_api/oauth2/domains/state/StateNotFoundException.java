package org.bkd.social_media_scheduler_api.oauth2.domains.state;

public class StateNotFoundException extends Exception {

  public StateNotFoundException() {
    super("State not found");
  }
}
