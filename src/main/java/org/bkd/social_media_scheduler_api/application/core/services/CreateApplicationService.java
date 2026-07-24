package org.bkd.social_media_scheduler_api.application.core.services;


import lombok.RequiredArgsConstructor;
import org.bkd.social_media_scheduler_api.application.core.ports.in.CreateApplicationUseCase;
import org.bkd.social_media_scheduler_api.application.core.ports.out.ApplicationRepository;
import org.bkd.social_media_scheduler_api.application.domain.Application;

import static java.util.UUID.randomUUID;

@RequiredArgsConstructor
public class CreateApplicationService implements CreateApplicationUseCase {

  private final ApplicationRepository applicationRepository;

  @Override
  public Application createApplication(String name, String description) {
    Application application = new Application(randomUUID(), name, description);
    return applicationRepository.save(application);
  }
}