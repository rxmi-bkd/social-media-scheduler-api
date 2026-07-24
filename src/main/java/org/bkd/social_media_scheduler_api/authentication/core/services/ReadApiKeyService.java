package org.bkd.social_media_scheduler_api.authentication.core.services;

import lombok.RequiredArgsConstructor;
import org.bkd.social_media_scheduler_api.authentication.core.ports.in.ReadApiKeyUseCase;
import org.bkd.social_media_scheduler_api.authentication.core.ports.out.ApiKeyRepository;
import org.bkd.social_media_scheduler_api.authentication.domains.ApiKey;
import org.bkd.social_media_scheduler_api.authentication.domains.ApiKeyNotFoundException;

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
