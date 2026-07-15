package org.bkd.social_media_scheduler_api.oauth2.adapters.out.state;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StateJpaRepository extends JpaRepository<StateEntity, UUID> {
  void deleteByIdAndApplicationId(UUID stateId, UUID applicationId);

  Optional<StateEntity> findByValueAndApplicationId(String value, UUID applicationId);
}
