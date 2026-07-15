package org.bkd.social_media_scheduler_api.application.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Application {

  private UUID id;

  private String name;

  private String description;

  public Application(String name, String description) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.description = description;
  }
}
