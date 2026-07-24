package org.bkd.social_media_scheduler_api.authentication.frameworks;

import org.bkd.social_media_scheduler_api.authentication.core.ports.in.CreateApiKeyUseCase;
import org.bkd.social_media_scheduler_api.authentication.core.ports.in.DeleteApiKeyUseCase;
import org.bkd.social_media_scheduler_api.authentication.core.ports.in.ReadApiKeyUseCase;
import org.bkd.social_media_scheduler_api.authentication.core.ports.out.ApiKeyRepository;
import org.bkd.social_media_scheduler_api.authentication.core.services.CreateApiKeyService;
import org.bkd.social_media_scheduler_api.authentication.core.services.DeleteApiKeyService;
import org.bkd.social_media_scheduler_api.authentication.core.services.ReadApiKeyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class AuthenticationConfiguration {


  @Bean
  @Transactional
  public CreateApiKeyUseCase apiKeyCreateService(ApiKeyRepository apiKeyRepository) {
    return new CreateApiKeyService(apiKeyRepository);
  }

  @Bean
  @Transactional
  public ReadApiKeyUseCase apiKeyReadService(ApiKeyRepository apiKeyRepository) {
    return new ReadApiKeyService(apiKeyRepository);
  }

  @Bean
  @Transactional
  public DeleteApiKeyUseCase apiKeyDeleteService(ApiKeyRepository apiKeyRepository) {
    return new DeleteApiKeyService(apiKeyRepository);
  }
}
