package org.bkd.social_media_scheduler_api.application.adapters.out;


import lombok.RequiredArgsConstructor;
import org.bkd.social_media_scheduler_api.application.core.ports.out.ApplicationRepository;
import org.bkd.social_media_scheduler_api.application.domain.Application;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class ApplicationSqlRepository implements ApplicationRepository {

  private final ApplicationMapper applicationMapper;
  private final ApplicationEntityMapper applicationEntityMapper;
  private final ApplicationJpaRepository applicationJpaRepository;

  @Override
  public Application save(Application application) {
    ApplicationEntity applicationEntity = applicationEntityMapper.toApplicationEntity(application);
    applicationEntity = applicationJpaRepository.save(applicationEntity);
    return applicationMapper.toApplication(applicationEntity);
  }

  @Override
  public List<Application> findAll() {
    List<ApplicationEntity> applicationEntities = applicationJpaRepository.findAll();
    return applicationMapper.toApplications(applicationEntities);
  }
}
