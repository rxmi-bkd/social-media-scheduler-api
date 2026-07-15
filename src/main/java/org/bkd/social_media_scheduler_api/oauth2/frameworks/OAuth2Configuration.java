package org.bkd.social_media_scheduler_api.oauth2.frameworks;

import org.bkd.social_media_scheduler_api.oauth2.core.ports.in.HandleCallbackUseCase;
import org.bkd.social_media_scheduler_api.oauth2.core.ports.in.InitiateOAuth2UseCase;
import org.bkd.social_media_scheduler_api.oauth2.core.ports.out.StateRepository;
import org.bkd.social_media_scheduler_api.oauth2.core.ports.out.TokenManager;
import org.bkd.social_media_scheduler_api.oauth2.core.ports.out.TokenRepository;
import org.bkd.social_media_scheduler_api.oauth2.core.services.AuthorizationUrlBuilder;
import org.bkd.social_media_scheduler_api.oauth2.core.services.OAuth2CallbackHandler;
import org.bkd.social_media_scheduler_api.oauth2.core.services.OAuth2Initializer;
import org.bkd.social_media_scheduler_api.oauth2.core.services.TiktokAuthorizationUrlBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Configuration
@EnableConfigurationProperties(PlatformsProperties.class)
public class OAuth2Configuration {

  @Bean
  @Transactional
  public AuthorizationUrlBuilder tiktokAuthorizationUrlBuilder(PlatformsProperties platformsProperties) {
    return new TiktokAuthorizationUrlBuilder(platformsProperties.getTiktok());
  }

  @Bean
  @Transactional
  public InitiateOAuth2UseCase OAuth2Initializer(List<AuthorizationUrlBuilder> authorizationUrlBuilders,
                                                 StateRepository stateRepository) {

    return new OAuth2Initializer(authorizationUrlBuilders, stateRepository);
  }

  @Bean
  @Transactional
  public HandleCallbackUseCase OAuth2CallbackHandler(StateRepository stateRepository,
                                                     TokenRepository tokenRepository,
                                                     List<TokenManager> tokenManagers) {

    return new OAuth2CallbackHandler(stateRepository, tokenRepository, tokenManagers);
  }
}

