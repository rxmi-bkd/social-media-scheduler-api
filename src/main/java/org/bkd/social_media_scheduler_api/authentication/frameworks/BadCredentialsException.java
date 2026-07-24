package org.bkd.social_media_scheduler_api.authentication.frameworks;

public class BadCredentialsException extends Exception {
  public BadCredentialsException() {
    super("Bad credentials");
  }
}
