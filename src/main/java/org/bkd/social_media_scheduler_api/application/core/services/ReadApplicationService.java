package org.bkd.social_media_scheduler_api.application.core.services;


import lombok.RequiredArgsConstructor;
import org.bkd.social_media_scheduler_api.application.core.ports.in.ReadApplicationUseCase;
import org.bkd.social_media_scheduler_api.application.core.ports.out.ApplicationRepository;
import org.bkd.social_media_scheduler_api.application.domain.Application;

import java.util.List;

@RequiredArgsConstructor
public class ReadApplicationService implements ReadApplicationUseCase {

  private final ApplicationRepository applicationRepository;

  @Override
  public List<Application> readApplications() {
    return applicationRepository.findAll();
  }
}

