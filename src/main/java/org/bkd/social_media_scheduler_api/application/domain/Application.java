package org.bkd.social_media_scheduler_api.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Application {

  private UUID id;

  private String name;

  private String description;
}
