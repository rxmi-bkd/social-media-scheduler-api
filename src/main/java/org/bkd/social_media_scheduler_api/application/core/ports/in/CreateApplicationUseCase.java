package org.bkd.social_media_scheduler_api.application.core.ports.in;


import org.bkd.social_media_scheduler_api.application.domain.Application;

public interface CreateApplicationUseCase {

  Application createApplication(String name, String description);
}
