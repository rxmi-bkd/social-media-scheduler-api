package org.bkd.social_media_scheduler_api.oauth2.adapters.out.token_managers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bkd.social_media_scheduler_api.oauth2.core.ports.out.ProfileService;
import org.bkd.social_media_scheduler_api.oauth2.core.ports.out.TokenManager;
import org.bkd.social_media_scheduler_api.oauth2.domains.platform.Platform;
import org.bkd.social_media_scheduler_api.oauth2.domains.token.Token;
import org.bkd.social_media_scheduler_api.oauth2.domains.token.TokenExchangeException;
import org.bkd.social_media_scheduler_api.oauth2.frameworks.PlatformProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.time.Instant;
import java.util.UUID;

import static java.time.Instant.now;
import static java.util.UUID.randomUUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TiktokTokenManager implements TokenManager {

  private final static RestClient restClient = RestClient.create();

  private final PlatformProperties platformProperties;

  private final ProfileService profileService;


  @Override
  public boolean supports(Platform platform) {
    return platform == Platform.tiktok;
  }

  @Override
  public Token exchangeCodeForToken(String code, UUID applicationId) throws TokenExchangeException {
    try {
      MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
      body.add("client_key", platformProperties.getTiktok().clientId());
      body.add("client_secret", platformProperties.getTiktok().clientSecret());
      body.add("code", code);
      body.add("grant_type", platformProperties.getTiktok().grantType());
      body.add("redirect_uri", platformProperties.getTiktok().redirectUri());
      return performRequest(body, applicationId);
    } catch (Exception e) {
      String errorMsg = "An error occurred while trying to exchange the code for the token";
      log.error(errorMsg, e);
      throw new TokenExchangeException(errorMsg);
    }
  }

  @Override
  public Token refreshToken(String refreshToken, UUID applicationId) throws TokenExchangeException {
    try {
      MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
      body.add("client_key", platformProperties.getTiktok().clientId());
      body.add("client_secret", platformProperties.getTiktok().clientSecret());
      body.add("grant_type", "refresh_token");
      body.add("refresh_token", refreshToken);
      return performRequest(body, applicationId);
    } catch (Exception e) {
      String errorMsg = "An error occurred while trying to refresh the token";
      log.error(errorMsg, e);
      throw new TokenExchangeException(errorMsg);
    }
  }

  private Token performRequest(MultiValueMap<String, String> body, UUID applicationId) {
    TiktokTokenResponse response = restClient.post()
                                             .uri(platformProperties.getTiktok().tokenUri())
                                             .body(body)
                                             .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                             .retrieve()
                                             .body(TiktokTokenResponse.class);

    Instant now = now();

    return new Token(randomUUID(),
                     response.getOpenId(),
                     response.getScope(),
                     response.getAccessToken(),
                     response.getRefreshToken(),
                     now.plusSeconds(response.getExpiresIn()),
                     now.plusSeconds(response.getRefreshExpiresIn()),
                     response.getTokenType(),
                     Platform.tiktok,
                     null,
                     null,
                     applicationId);
  }

}
