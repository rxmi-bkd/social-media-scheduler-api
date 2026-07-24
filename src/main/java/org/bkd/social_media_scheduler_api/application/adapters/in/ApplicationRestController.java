package org.bkd.social_media_scheduler_api.application.adapters.in;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.bkd.social_media_scheduler_api.application.core.ports.in.CreateApplicationUseCase;
import org.bkd.social_media_scheduler_api.application.core.ports.in.ReadApplicationUseCase;
import org.bkd.social_media_scheduler_api.application.domain.Application;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.bkd.social_media_scheduler_api.authentication.frameworks.SecurityConfiguration.ADMIN_BASE_PATH;

@Validated
@RestController
@RequiredArgsConstructor
public class ApplicationRestController {

  private final ReadApplicationUseCase applicationReadService;
  private final CreateApplicationUseCase applicationCreateService;
  private final ApplicationResponseMapper applicationResponseMapper;

  @PostMapping(ADMIN_BASE_PATH + "/application")
  public ApplicationResponse createApplication(@RequestBody @NotBlank String name,
                                               @RequestBody @NotBlank String description) {

    Application application = applicationCreateService.createApplication(name, description);
    return applicationResponseMapper.toApplicationResponse(application);
  }

  @GetMapping(ADMIN_BASE_PATH + "/applications")
  public List<ApplicationResponse> readApplications() {
    List<Application> applications = applicationReadService.readApplications();
    return applicationResponseMapper.toApplicationResponses(applications);
  }
}
