package org.bkd.social_media_scheduler_api.oauth2.core.services;


import lombok.RequiredArgsConstructor;
import org.bkd.social_media_scheduler_api.oauth2.core.ports.in.HandleCallbackUseCase;
import org.bkd.social_media_scheduler_api.oauth2.core.ports.out.StateRepository;
import org.bkd.social_media_scheduler_api.oauth2.core.ports.out.TokenManager;
import org.bkd.social_media_scheduler_api.oauth2.core.ports.out.TokenRepository;
import org.bkd.social_media_scheduler_api.oauth2.domains.platform.Platform;
import org.bkd.social_media_scheduler_api.oauth2.domains.platform.UnsupportedPlatformException;
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

  @Override
  public Token handleOAuth2Callback(String code,
                                    String scopes,
                                    String state,
                                    Platform platform,
                                    UUID applicationId) throws
                                                        UnsupportedPlatformException,
                                                        StateNotFoundException,
                                                        TokenExchangeException,
                                                        StateExpiredException {

    State oAuth2State = stateRepository.findByValueAndApplicationId(state, applicationId)
                                       .orElseThrow(StateNotFoundException::new);

    if (oAuth2State.isExpired()) {
      throw new StateExpiredException();
    }

    List<TokenManager> tokenManagers = this.tokenManagers.stream().filter(s -> s.supports(platform)).toList();

    if (tokenManagers.isEmpty()) {
      throw new UnsupportedPlatformException(platform);
    }

    if (tokenManagers.size() != 1) {
      throw new IllegalStateException("Multiple exchangers found for platform: " + platform);
    }

    TokenManager tokenManager = tokenManagers.getFirst();
    Token token = tokenManager.exchangeCodeForToken(code, scopes, state, applicationId);
    token = tokenRepository.save(token);
    stateRepository.deleteByIdAndApplicationId(oAuth2State.getId(), applicationId);
    return token;
  }
}
