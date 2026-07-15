package org.bkd.social_media_scheduler_api.oauth2.adapters.out.token;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenJpaRepository extends JpaRepository<TokenEntity, UUID> {

  void deleteByIdAndApplicationId(UUID tokenId, UUID applicationId);

  Optional<TokenEntity> findByIdAndApplicationId(UUID tokenId, UUID applicationId);

  List<TokenEntity> findAllByApplicationId(UUID applicationId);

}
