package org.bkd.social_media_scheduler_api.oauth2.core.services;


import lombok.RequiredArgsConstructor;
import org.bkd.social_media_scheduler_api.oauth2.core.ports.in.InitiateOAuth2UseCase;
import org.bkd.social_media_scheduler_api.oauth2.core.ports.out.StateRepository;
import org.bkd.social_media_scheduler_api.oauth2.domains.platform.Platform;
import org.bkd.social_media_scheduler_api.oauth2.domains.platform.UnsupportedPlatformException;
import org.bkd.social_media_scheduler_api.oauth2.domains.state.State;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import static java.time.Instant.now;
import static java.util.UUID.randomUUID;
import static org.bkd.social_media_scheduler_api.oauth2.domains.state.State.STATE_SEPARATOR;

@RequiredArgsConstructor
public class OAuth2Initializer implements InitiateOAuth2UseCase {

  private final List<AuthorizationUrlBuilder> oauth2AuthorizationUrlBuilders;
  private final StateRepository stateRepository;

  @Override
  public String initiateOAuth2(Platform platform, UUID applicationId) throws UnsupportedPlatformException {

    List<AuthorizationUrlBuilder> urlBuilders = oauth2AuthorizationUrlBuilders.stream()
                                                                              .filter(p -> p.supports(platform))
                                                                              .toList();

    if (urlBuilders.isEmpty()) {
      throw new UnsupportedPlatformException(platform);
    }

    if (urlBuilders.size() != 1) {
      throw new IllegalStateException("Multiple url builders found for platform: " + platform);
    }

    AuthorizationUrlBuilder urlBuilder = urlBuilders.getFirst();

    String authorizationUrl = urlBuilder.buildAuthorizationUrl(applicationId);

    // State is used for CSRF protection. It will be checked during callback.
    // We also send application ID in order to track which application initiated the OAuth2 flow
    State state = buildState(applicationId);
    stateRepository.save(state);

    return authorizationUrl + "&state=" + String.join(STATE_SEPARATOR,
                                                      state.getValue(),
                                                      applicationId.toString());
  }

  private String generateState() {
    byte[] randomBytes = new byte[32];
    new SecureRandom().nextBytes(randomBytes);
    return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
  }

  private State buildState(UUID applicationId) {
    State state = new State();
    state.setId(randomUUID());
    state.setValue(generateState());
    state.setCreatedAt(now());
    state.setApplicationId(applicationId);
    return state;
  }
}
