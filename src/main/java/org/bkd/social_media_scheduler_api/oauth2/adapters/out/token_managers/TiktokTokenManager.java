package org.bkd.social_media_scheduler_api.oauth2.adapters.out.token_managers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bkd.social_media_scheduler_api.oauth2.core.ports.out.TokenManager;
import org.bkd.social_media_scheduler_api.oauth2.domains.platform.Platform;
import org.bkd.social_media_scheduler_api.oauth2.domains.token.Token;
import org.bkd.social_media_scheduler_api.oauth2.domains.token.TokenExchangeException;
import org.bkd.social_media_scheduler_api.oauth2.frameworks.PlatformsProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import static org.springframework.util.StringUtils.hasText;

@Slf4j
@Service
@RequiredArgsConstructor
public class TiktokTokenManager implements TokenManager {

  private final static RestClient restClient = RestClient.create();

  private final PlatformsProperties platformsProperties;


  @Override
  public boolean supports(Platform platform) {
    return platform == Platform.tiktok;
  }

  @Override
  public Token exchangeCodeForToken(String code, String scopes, String state, UUID applicationId) throws
                                                                                                  TokenExchangeException {

    MultiValueMap<String, String> body = buildRequestBody(code);
    Map<String, Object> response = performRequest(body);
    return buildOAuth2Token(response, applicationId);

  }

  private Token buildOAuth2Token(Map<String, Object> response, UUID applicationId) throws TokenExchangeException {

    String openId = (String) response.get("open_id");
    String scope = (String) response.get("scope");
    String accessToken = (String) response.get("access_token");
    String refreshToken = (String) response.get("refresh_token");
    Integer accessTokenExpiresInSeconds = (Integer) response.get("expires_in");
    Integer refreshExpiresInSeconds = (Integer) response.get("refresh_expires_in");
    String tokenType = (String) response.get("token_type");
    String error = (String) response.get("error");
    String errorDescription = (String) response.get("error_description");

    if (hasText(error)) {
      throw new TokenExchangeException(errorDescription);
    }

    Instant now = Instant.now();

    return new Token(openId,
                     scope,
                     accessToken,
                     refreshToken,
                     now.plusSeconds(accessTokenExpiresInSeconds),
                     now.plusSeconds(refreshExpiresInSeconds),
                     tokenType,
                     Platform.tiktok,
                     applicationId);
  }

  private Map<String, Object> performRequest(MultiValueMap<String, String> body) throws TokenExchangeException {

    try {
      org.bkd.social_media_scheduler_api.oauth2.frameworks.Platform properties = getProperties();
      String uri = properties.tokenUri().endsWith("/") ? properties.tokenUri() : properties.tokenUri() + "/";

      return restClient.post()
                       .uri(uri)
                       .body(body)
                       .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                       .retrieve()
                       .body(new ParameterizedTypeReference<Map<String, Object>>() {
                       });

    } catch (Exception e) {
      throw new TokenExchangeException("An error occurred while exchanging tokens with tiktok server");
    }
  }

  private MultiValueMap<String, String> buildRequestBody(String code) {
    org.bkd.social_media_scheduler_api.oauth2.frameworks.Platform properties = getProperties();
    MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
    body.add("client_key", properties.clientId());
    body.add("client_secret", properties.clientSecret());
    body.add("code", code);
    body.add("grant_type", properties.grantType());
    body.add("redirect_uri", properties.redirectUri());
    return body;
  }

  private org.bkd.social_media_scheduler_api.oauth2.frameworks.Platform getProperties() {
    return platformsProperties.getTiktok();
  }
}
