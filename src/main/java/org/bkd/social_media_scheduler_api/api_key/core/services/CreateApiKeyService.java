package org.bkd.social_media_scheduler_api.api_key.core.services;

import lombok.RequiredArgsConstructor;
import org.bkd.social_media_scheduler_api.api_key.core.ports.in.CreateApiKeyUseCase;
import org.bkd.social_media_scheduler_api.api_key.core.ports.out.ApiKeyRepository;
import org.bkd.social_media_scheduler_api.api_key.domains.ApiKey;

import java.util.UUID;

@RequiredArgsConstructor
public class CreateApiKeyService implements CreateApiKeyUseCase {

  private final ApiKeyRepository apiKeyRepository;

  @Override
  public ApiKey createApiKey(UUID applicationId) {
    ApiKey apiKey = new ApiKey(applicationId);
    apiKey = apiKeyRepository.save(apiKey);
    return apiKey;
  }
}
