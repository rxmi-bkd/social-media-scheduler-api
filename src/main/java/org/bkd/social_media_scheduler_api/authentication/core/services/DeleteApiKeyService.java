package org.bkd.social_media_scheduler_api.authentication.core.services;

import lombok.RequiredArgsConstructor;
import org.bkd.social_media_scheduler_api.authentication.core.ports.in.DeleteApiKeyUseCase;
import org.bkd.social_media_scheduler_api.authentication.core.ports.out.ApiKeyRepository;

import java.util.UUID;

@RequiredArgsConstructor
public class DeleteApiKeyService implements DeleteApiKeyUseCase {

  private final ApiKeyRepository apiKeyRepository;

  @Override
  public void deleteApiKey(UUID apiKeyId, UUID applicationId) {
    apiKeyRepository.deleteByIdAndApplicationId(apiKeyId, applicationId);
  }
}
