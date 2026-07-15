package org.bkd.social_media_scheduler_api.oauth2.domains.token;

public class TokenNotFoundException extends Exception {

  public TokenNotFoundException() {
    super("Token not found");
  }
}
