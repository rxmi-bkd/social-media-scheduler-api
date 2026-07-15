package org.bkd.social_media_scheduler_api.oauth2.core.ports.out;


import org.bkd.social_media_scheduler_api.oauth2.domains.state.State;

import java.util.Optional;
import java.util.UUID;

public interface StateRepository {

  State save(State state);

  Optional<State> findByValueAndApplicationId(String value, UUID applicationId);

  void deleteByIdAndApplicationId(UUID stateId, UUID applicationId);
}

