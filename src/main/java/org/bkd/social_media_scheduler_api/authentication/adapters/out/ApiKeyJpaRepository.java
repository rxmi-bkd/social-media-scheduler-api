package org.bkd.social_media_scheduler_api.authentication.adapters.out;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApiKeyJpaRepository extends JpaRepository<ApiKeyEntity, UUID> {

  Optional<ApiKeyEntity> findByValueAndApplicationId(String value, UUID applicationId);

  void deleteByIdAndApplicationId(UUID apiKeyId, UUID applicationId);

  List<ApiKeyEntity> findAllByApplicationId(UUID applicationId);
}
