package org.bkd.social_media_scheduler_api.oauth2.adapters.out.state;


import lombok.RequiredArgsConstructor;
import org.bkd.social_media_scheduler_api.oauth2.core.ports.out.StateRepository;
import org.bkd.social_media_scheduler_api.oauth2.domains.state.State;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
@RequiredArgsConstructor
public class StateSqlRepository implements StateRepository {

  private final StateMapper stateMapper;
  private final StateEntityMapper stateEntityMapper;
  private final StateJpaRepository stateJpaRepository;

  @Override
  public State save(State state) {
    StateEntity newStateEntity = stateEntityMapper.toStateEntity(state);
    newStateEntity = stateJpaRepository.save(newStateEntity);
    return stateMapper.toState(newStateEntity);
  }

  @Override
  public Optional<State> findByValueAndApplicationId(String value, UUID applicationId) {
    return stateJpaRepository.findByValueAndApplicationId(value, applicationId).map(stateMapper::toState);
  }

  @Override
  public void deleteByIdAndApplicationId(UUID stateId, UUID applicationId) {
    stateJpaRepository.deleteByIdAndApplicationId(stateId, applicationId);
  }
}
