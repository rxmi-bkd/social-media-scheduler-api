package org.bkd.social_media_scheduler_api.api_key.frameworks;

import org.bkd.social_media_scheduler_api.api_key.core.ports.in.CreateApiKeyUseCase;
import org.bkd.social_media_scheduler_api.api_key.core.ports.in.DeleteApiKeyUseCase;
import org.bkd.social_media_scheduler_api.api_key.core.ports.in.ReadApiKeyUseCase;
import org.bkd.social_media_scheduler_api.api_key.core.ports.out.ApiKeyRepository;
import org.bkd.social_media_scheduler_api.api_key.core.services.CreateApiKeyService;
import org.bkd.social_media_scheduler_api.api_key.core.services.DeleteApiKeyService;
import org.bkd.social_media_scheduler_api.api_key.core.services.ReadApiKeyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class ApiKeyConfiguration {


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
