package org.bkd.social_media_scheduler_api.application.adapters.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateApplicationRequest {

  @NotBlank
  private String name;

  @NotBlank
  private String description;
}
