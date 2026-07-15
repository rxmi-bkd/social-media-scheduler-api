package org.bkd.social_media_scheduler_api.application.adapters.out;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplicationJpaRepository extends JpaRepository<ApplicationEntity, UUID> {
}
