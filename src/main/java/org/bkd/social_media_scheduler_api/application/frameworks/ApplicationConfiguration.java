package org.bkd.social_media_scheduler_api.application.frameworks;

import org.bkd.social_media_scheduler_api.application.core.ports.in.CreateApplicationUseCase;
import org.bkd.social_media_scheduler_api.application.core.ports.in.ReadApplicationUseCase;
import org.bkd.social_media_scheduler_api.application.core.ports.out.ApplicationRepository;
import org.bkd.social_media_scheduler_api.application.core.services.CreateApplicationService;
import org.bkd.social_media_scheduler_api.application.core.services.ReadApplicationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class ApplicationConfiguration {

  @Bean
  @Transactional
  public CreateApplicationUseCase applicationCreateService(ApplicationRepository applicationRepository) {
    return new CreateApplicationService(applicationRepository);
  }

  @Bean
  @Transactional
  public ReadApplicationUseCase applicationReadService(ApplicationRepository applicationRepository) {
    return new ReadApplicationService(applicationRepository);
  }
}
