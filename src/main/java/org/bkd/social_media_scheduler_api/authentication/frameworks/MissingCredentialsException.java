package org.bkd.social_media_scheduler_api.authentication.frameworks;

public class MissingCredentialsException extends Exception{
  public MissingCredentialsException() {
    super("Missing credentials");
  }
}
