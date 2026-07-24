package org.bkd.social_media_scheduler_api.oauth2.adapters.in;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.bkd.social_media_scheduler_api.authentication.frameworks.TenantContext;
import org.bkd.social_media_scheduler_api.oauth2.core.ports.in.HandleCallbackUseCase;
import org.bkd.social_media_scheduler_api.oauth2.core.ports.in.InitiateOAuth2UseCase;
import org.bkd.social_media_scheduler_api.oauth2.domains.platform.Platform;
import org.bkd.social_media_scheduler_api.oauth2.domains.platform.UnsupportedPlatformException;
import org.bkd.social_media_scheduler_api.oauth2.domains.profile.ProfileRetrievalException;
import org.bkd.social_media_scheduler_api.oauth2.domains.state.StateExpiredException;
import org.bkd.social_media_scheduler_api.oauth2.domains.state.StateNotFoundException;
import org.bkd.social_media_scheduler_api.oauth2.domains.token.TokenExchangeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static org.bkd.social_media_scheduler_api.authentication.frameworks.SecurityConfiguration.BASE_PATH;
import static org.bkd.social_media_scheduler_api.oauth2.domains.state.State.STATE_SEPARATOR;

@RestController
@RequiredArgsConstructor
public class OAuth2RestController {

  private final InitiateOAuth2UseCase oAuth2Initializer;
  private final HandleCallbackUseCase oAuthCallbackHandler;

  @GetMapping(BASE_PATH + "/oauth/{platform}/authorize")
  public AuthorizationUrlResponse initialize(@PathVariable String platform) {
    UUID applicationId = TenantContext.getTenant();
    Platform p = resolvePlatform(platform);
    String authorizationUrl = initialize_(p, applicationId);
    return new AuthorizationUrlResponse(authorizationUrl);
  }

  private String initialize_(Platform platform, UUID applicationId) {
    try {
      return oAuth2Initializer.initiateOAuth2(platform, applicationId);
    } catch (UnsupportedPlatformException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  private Platform resolvePlatform(String platform) {
    try {
      return Platform.valueOf(platform);
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unsupported platform: " + platform);
    }
  }

  @GetMapping(BASE_PATH + "/login/oauth/code/{platform}")
  public void handleCallback(@PathVariable String platform, HttpServletRequest request) {
    Platform p = resolvePlatform(platform);
    String code = extractFromRequest(request, "code");
    String scopes = extractFromRequest(request, "scopes");
    String state = extractFromRequest(request, "state");
    UUID applicationId = extractApplicationIdFromState(state);
    state = removeApplicationIdFromState(state);
    handleCallback_(code, scopes, state, p, applicationId);
  }

  private void handleCallback_(String code, String scopes, String state, Platform platform, UUID applicationId) {
    try {
      oAuthCallbackHandler.handleOAuth2Callback(code, scopes, state, platform, applicationId);
    } catch (UnsupportedPlatformException | StateNotFoundException | StateExpiredException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    } catch (TokenExchangeException | ProfileRetrievalException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
  }

  private UUID extractApplicationIdFromState(String state) {
    try {
      String applicationId = state.split(STATE_SEPARATOR)[1];
      return UUID.fromString(applicationId);
    } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "State is invalid");
    }
  }

  private String removeApplicationIdFromState(String state) {
    return state.split(STATE_SEPARATOR)[0];
  }

  private String extractFromRequest(HttpServletRequest request, String key) {
    String value = request.getParameter(key);

    if (value == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required parameter " + key);
    }

    return value;
  }
}
