package org.bkd.social_media_scheduler_api.oauth2.core.services;


import lombok.RequiredArgsConstructor;
import org.bkd.social_media_scheduler_api.oauth2.domains.platform.Platform;

import java.util.UUID;

@RequiredArgsConstructor
public class TiktokAuthorizationUrlBuilder implements AuthorizationUrlBuilder {

  private final String authorizationUri;
  private final String clientId;
  private final String responseType;
  private final String scope;
  private final String redirectUri;

  @Override
  public boolean supports(Platform platform) {
    return platform == Platform.tiktok;
  }

  @Override
  public String buildAuthorizationUrl(UUID applicationId) {
    return String.format(authorizationUri + "?client_key=%s&response_type=%s&scope=%s&redirect_uri=%s",
                         clientId,
                         responseType,
                         scope,
                         redirectUri);
  }
}
