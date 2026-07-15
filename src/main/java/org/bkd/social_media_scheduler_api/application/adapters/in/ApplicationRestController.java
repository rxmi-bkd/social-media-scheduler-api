package org.bkd.social_media_scheduler_api.application.adapters.in;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bkd.social_media_scheduler_api.application.core.ports.in.CreateApplicationUseCase;
import org.bkd.social_media_scheduler_api.application.core.ports.in.ReadApplicationUseCase;
import org.bkd.social_media_scheduler_api.application.domain.Application;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.bkd.social_media_scheduler_api.security.SecurityConfiguration.ADMIN_BASE_PATH;

@RestController
@RequiredArgsConstructor
public class ApplicationRestController {

  private final ReadApplicationUseCase applicationReadService;
  private final CreateApplicationUseCase applicationCreateService;
  private final ApplicationResponseMapper applicationResponseMapper;

  @PostMapping(ADMIN_BASE_PATH + "/application")
  public ApplicationResponse createApplication(@Valid @RequestBody CreateApplicationRequest request) {
    Application application = applicationCreateService.createApplication(request.getName(), request.getDescription());
    return applicationResponseMapper.toApplicationResponse(application);
  }

  @GetMapping(ADMIN_BASE_PATH + "/applications")
  public List<ApplicationResponse> readApplications() {
    List<Application> applications = applicationReadService.readApplications();
    return applicationResponseMapper.toApplicationResponses(applications);
  }
}
