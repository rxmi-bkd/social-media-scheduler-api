package org.bkd.social_media_scheduler_api.authentication.adapters.out;


import lombok.RequiredArgsConstructor;
import org.bkd.social_media_scheduler_api.authentication.core.ports.out.ApiKeyRepository;
import org.bkd.social_media_scheduler_api.authentication.domains.ApiKey;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
@Transactional
@RequiredArgsConstructor
public class ApiKeySqlRepository implements ApiKeyRepository {

  private final ApiKeyMapper apiKeyMapper;
  private final ApiKeyEntityMapper apiKeyEntityMapper;
  private final ApiKeyJpaRepository apiKeyJpaRepository;

  @Override
  public ApiKey save(ApiKey apiKey) {
    ApiKeyEntity newApiKeyEntity = apiKeyEntityMapper.toApiKeyEntity(apiKey);
    newApiKeyEntity = apiKeyJpaRepository.save(newApiKeyEntity);
    return apiKeyMapper.toApiKey(newApiKeyEntity);
  }

  @Override
  public void deleteByIdAndApplicationId(UUID apiKeyId, UUID applicationId) {
    apiKeyJpaRepository.deleteByIdAndApplicationId(apiKeyId, applicationId);
  }

  @Override
  public Optional<ApiKey> findByValueAndApplicationId(String value, UUID applicationId) {
    return apiKeyJpaRepository.findByValueAndApplicationId(value, applicationId).map(apiKeyMapper::toApiKey);
  }

  @Override
  public List<ApiKey> findAllByApplicationId(UUID applicationId) {
    List<ApiKeyEntity> apiKeyEntities = apiKeyJpaRepository.findAllByApplicationId(applicationId);
    return apiKeyMapper.toApiKeys(apiKeyEntities);
  }
}
