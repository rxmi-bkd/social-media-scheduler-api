package org.bkd.social_media_scheduler_api.security;


import org.bkd.social_media_scheduler_api.api_key.domains.ApiKey;

public class AuthenticationContext {

  private static final ThreadLocal<ApiKey> apiKey = new ThreadLocal<>();

  public static ApiKey getAuthentication() {
    if (AuthenticationContext.apiKey.get() == null) {
      throw new IllegalStateException();
    }

    return AuthenticationContext.apiKey.get();
  }

  public static void setAuthentication(ApiKey apiKey) {
    AuthenticationContext.apiKey.set(apiKey);
  }

  public static void clear() {
    AuthenticationContext.apiKey.remove();
  }
}
