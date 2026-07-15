package org.bkd.social_media_scheduler_api.application.core.ports.in;


import org.bkd.social_media_scheduler_api.application.domain.Application;

import java.util.List;

public interface ReadApplicationUseCase {

  List<Application> readApplications();
}
