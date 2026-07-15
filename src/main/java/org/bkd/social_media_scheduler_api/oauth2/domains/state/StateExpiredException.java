package org.bkd.social_media_scheduler_api.oauth2.domains.state;

public class StateExpiredException extends Exception {

  public StateExpiredException() {
    super("State has expired");
  }
}
