package org.bkd.social_media_scheduler_api.authentication.core.services;

import lombok.RequiredArgsConstructor;
import org.bkd.social_media_scheduler_api.authentication.core.ports.in.CreateApiKeyUseCase;
import org.bkd.social_media_scheduler_api.authentication.core.ports.out.ApiKeyRepository;
import org.bkd.social_media_scheduler_api.authentication.domains.ApiKey;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.bkd.social_media_scheduler_api.authentication.domains.Role.USER;

@RequiredArgsConstructor
public class CreateApiKeyService implements CreateApiKeyUseCase {

  private final ApiKeyRepository apiKeyRepository;

  @Override
  public ApiKey createApiKey(UUID applicationId) {
    ApiKey apiKey = new ApiKey(randomUUID(), USER, generateApiKeyValue(), applicationId);
    apiKey = apiKeyRepository.save(apiKey);
    return apiKey;
  }

  private String generateApiKeyValue() {
    byte[] randomBytes = new byte[32];
    SecureRandom secureRandom = new SecureRandom();
    secureRandom.nextBytes(randomBytes);
    return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
  }

}
