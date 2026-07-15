package org.bkd.social_media_scheduler_api.application.adapters.in;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ApplicationResponse {

  private UUID id;

  private String name;

  private String description;
}
