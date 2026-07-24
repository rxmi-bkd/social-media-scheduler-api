package org.bkd.social_media_scheduler_api.authentication.domains;

public class ApiKeyNotFoundException extends Exception {

  public ApiKeyNotFoundException() {
    super("API key not found");
  }
}
