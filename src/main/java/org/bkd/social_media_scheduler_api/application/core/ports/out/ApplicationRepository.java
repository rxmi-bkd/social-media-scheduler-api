package org.bkd.social_media_scheduler_api.application.core.ports.out;


import org.bkd.social_media_scheduler_api.application.domain.Application;

import java.util.List;

public interface ApplicationRepository {

  Application save(Application application);

  List<Application> findAll();
}
