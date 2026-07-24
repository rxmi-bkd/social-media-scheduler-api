package org.bkd.social_media_scheduler_api.oauth2.core.services;


import lombok.RequiredArgsConstructor;
import org.bkd.social_media_scheduler_api.oauth2.core.ports.in.HandleCallbackUseCase;
import org.bkd.social_media_scheduler_api.oauth2.core.ports.out.ProfileService;
import org.bkd.social_media_scheduler_api.oauth2.core.ports.out.StateRepository;
import org.bkd.social_media_scheduler_api.oauth2.core.ports.out.TokenManager;
import org.bkd.social_media_scheduler_api.oauth2.core.ports.out.TokenRepository;
import org.bkd.social_media_scheduler_api.oauth2.domains.platform.Platform;
import org.bkd.social_media_scheduler_api.oauth2.domains.platform.UnsupportedPlatformException;
import org.bkd.social_media_scheduler_api.oauth2.domains.profile.Profile;
import org.bkd.social_media_scheduler_api.oauth2.domains.profile.ProfileRetrievalException;
import org.bkd.social_media_scheduler_api.oauth2.domains.state.State;
import org.bkd.social_media_scheduler_api.oauth2.domains.state.StateExpiredException;
import org.bkd.social_media_scheduler_api.oauth2.domains.state.StateNotFoundException;
import org.bkd.social_media_scheduler_api.oauth2.domains.token.Token;
import org.bkd.social_media_scheduler_api.oauth2.domains.token.TokenExchangeException;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class OAuth2CallbackHandler implements HandleCallbackUseCase {

  private final StateRepository stateRepository;
  private final TokenRepository tokenRepository;
  private final List<TokenManager> tokenManagers;
  private final List<ProfileService> profileServices;

  @Override
  public Token handleOAuth2Callback(String code,
                                    String scopes,
                                    String state,
                                    Platform platform,
                                    UUID applicationId) throws
                                                        UnsupportedPlatformException,
                                                        StateNotFoundException,
                                                        TokenExchangeException,
                                                        StateExpiredException,
                                                        ProfileRetrievalException {

    State state_ = stateRepository.findByValueAndApplicationId(state, applicationId)
                                  .orElseThrow(StateNotFoundException::new);

    if (state_.isExpired()) {
      throw new StateExpiredException();
    }

    TokenManager tokenManager = getTokenManager(platform);
    Token token = tokenManager.exchangeCodeForToken(code, applicationId);

    String accessToken = token.getAccessToken();

    ProfileService profileService = getProfileService(platform);
    Profile profile = profileService.getProfile(accessToken);

    token.setDisplayName(profile.getDisplayName());
    token.setAvatarUrl(profile.getAvatarUrl());

    token = tokenRepository.save(token);

    stateRepository.deleteByIdAndApplicationId(state_.getId(), applicationId);
    return token;
  }

  private ProfileService getProfileService(Platform platform) throws UnsupportedPlatformException {
    List<ProfileService> profileServices = this.profileServices.stream().filter(s -> s.supports(platform)).toList();

    if (profileServices.isEmpty()) {
      throw new UnsupportedPlatformException(platform);
    }

    if (profileServices.size() != 1) {
      throw new IllegalStateException("Multiple profile services found for platform: " + platform);
    }

    return profileServices.getFirst();
  }

  private TokenManager getTokenManager(Platform platform) throws UnsupportedPlatformException {
    List<TokenManager> tokenManagers = this.tokenManagers.stream().filter(s -> s.supports(platform)).toList();

    if (tokenManagers.isEmpty()) {
      throw new UnsupportedPlatformException(platform);
    }

    if (tokenManagers.size() != 1) {
      throw new IllegalStateException("Multiple exchangers found for platform: " + platform);
    }

    return tokenManagers.getFirst();
  }
}
