package org.bkd.social_media_scheduler_api.api_key.core.services;

import lombok.RequiredArgsConstructor;
import org.bkd.social_media_scheduler_api.api_key.core.ports.in.ReadApiKeyUseCase;
import org.bkd.social_media_scheduler_api.api_key.core.ports.out.ApiKeyRepository;
import org.bkd.social_media_scheduler_api.api_key.domains.ApiKey;
import org.bkd.social_media_scheduler_api.api_key.domains.ApiKeyNotFoundException;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ReadApiKeyService implements ReadApiKeyUseCase {

  private final ApiKeyRepository apiKeyRepository;

  @Override
  public ApiKey readApiKey(String apiKeyValue, UUID applicationId) throws ApiKeyNotFoundException {
    return apiKeyRepository.findByValueAndApplicationId(apiKeyValue, applicationId)
                           .orElseThrow(ApiKeyNotFoundException::new);
  }

  @Override
  public List<ApiKey> readApiKeys(UUID applicationId) {
    return apiKeyRepository.findAllByApplicationId(applicationId);
  }
}
