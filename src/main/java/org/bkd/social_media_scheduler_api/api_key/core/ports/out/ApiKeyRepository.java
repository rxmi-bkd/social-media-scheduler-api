package org.bkd.social_media_scheduler_api.api_key.core.ports.out;

import org.bkd.social_media_scheduler_api.api_key.domains.ApiKey;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApiKeyRepository {

  ApiKey save(ApiKey apiKey);

  void deleteByIdAndApplicationId(UUID apiKeyId, UUID applicationId);

  Optional<ApiKey> findByValueAndApplicationId(String value, UUID applicationId);

  List<ApiKey> findAllByApplicationId(UUID applicationId);
}
